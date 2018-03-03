package com.ubk.mysoncrud;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ubk.mysoncrud.classes.adapter.ViolationAdapter;
import com.ubk.mysoncrud.classes.helper.RecyclerItemTouchHelper;
import com.ubk.mysoncrud.classes.list.ViolationList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Violation extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    AlphaAnimation inAnimation, outAnimation;

    FrameLayout progressBarHolder;

    private RecyclerView rcv;
    private ViolationAdapter adapter;
    private List<ViolationList> violationList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Violation.this, MainMenu.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview_insert);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.violation);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Violation.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
        Button btnInsert = findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Violation.this, ViolationInsert.class);
                startActivity(intent);
                finish();
            }
        });

        progressBarHolder = findViewById(R.id.progressBarHolder);
        rcv = findViewById(R.id.recycler_view);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rcv);

        loadData();
    }

    private void loadData() {
        showProgress();
        String url = "http://mysonschool.web.id/MySonSchool/MySonSchoolStaffPhp/Violation/SelectViolation.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("Violation");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        ViolationList violation = new ViolationList(
                                o.getString("IdPelanggaran"),
                                o.getString("NamaPelanggaran"),
                                o.getString("PointPelanggaran"),
                                o.getString("TanggalPelanggaran")
                        );
                        violationList.add(violation);
                    }
                    adapter = new ViolationAdapter(violationList, getApplicationContext());
                    rcv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hideProgress();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgress();
                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showProgress() {
        rcv.setEnabled(false);
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolder.setAnimation(outAnimation);
        progressBarHolder.setVisibility(View.GONE);
        rcv.setEnabled(true);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ViolationAdapter.ViewHolder) {
            if (direction == ItemTouchHelper.LEFT) {
                sureDelete(viewHolder);
            } else {
                sureEdit(viewHolder);
            }
        }
    }

    private void sureEdit(final RecyclerView.ViewHolder viewHolder) {
        String name = violationList.get(viewHolder.getAdapterPosition()).getViolationname();
        final SweetAlertDialog editDialog = new SweetAlertDialog(this);
        editDialog.setTitleText("Edit " + name + "?");
        editDialog.setConfirmText("Yes");
        editDialog.setCancelText("No");
        editDialog.setCanceledOnTouchOutside(false);
        editDialog.showCancelButton(true);
        editDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                edit(viewHolder);
                editDialog.cancel();
                //adapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }
        });
        editDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                //adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                editDialog.cancel();
            }
        });
        editDialog.show();
    }

    private void edit(RecyclerView.ViewHolder viewHolder) {
        String violationid = violationList.get(viewHolder.getAdapterPosition()).getViolationid();
        Intent intent = new Intent(this, ViolationEdit.class);
        intent.putExtra("violationid", violationid);
        startActivity(intent);
    }

    private void sureDelete(final RecyclerView.ViewHolder viewHolder) {
        String name = violationList.get(viewHolder.getAdapterPosition()).getViolationname();
        final SweetAlertDialog deleteDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        deleteDialog.setTitleText("Are you sure you want to delete " + name + "?");
        deleteDialog.setConfirmText("Yes, Delete it!");
        deleteDialog.setCancelText("No, Don't delete it!");
        deleteDialog.setCanceledOnTouchOutside(false);
        deleteDialog.showCancelButton(true);
        deleteDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                delete(viewHolder);
            }
        });
        deleteDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                deleteDialog.cancel();
            }
        });
        deleteDialog.show();
    }

    private void delete(RecyclerView.ViewHolder viewHolder) {
        // get the removed item name to display it in snack bar
        String name = violationList.get(viewHolder.getAdapterPosition()).getViolationname();

        // backup of removed item for undo purpose
        final ViolationList deletedItem = violationList.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();

        // remove the item from recycler view
        adapter.removeItem(viewHolder.getAdapterPosition());

        // showing snack bar with Undo option
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, name + " removed!", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // undo is selected, restore the deleted item
               adapter.restoreItem(deletedItem, deletedIndex);
            }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }
}