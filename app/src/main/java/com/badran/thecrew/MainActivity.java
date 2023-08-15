package com.badran.thecrew;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.badran.thecrew.databinding.ActivityMainBinding;
import com.badran.thecrew.databinding.PopupWindowBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements TheCrewAdapter.AdapterCallback {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private PopupWindowBinding popupWindowBinding;
    public static final ArrayList<TheCrew> names = new ArrayList<>();
    public static final ArrayList<TheCrew> strings = new ArrayList<>();
    private TheCrewAdapter theCrewAdapter;
    private FinalResultAdapter finalResultAdapter;
    private int nescafeCount, nCanderelCount, nSugarCount, nNestleCount, nBlackCount, nCoffeeMateCount;
    private int coffeeCount, cCanderelCount, cSugarCount, cNestleCount, cBlackCount, cCoffeeMateCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        popupWindowBinding = PopupWindowBinding.inflate(getLayoutInflater());
        theCrewAdapter = new TheCrewAdapter(names, getLayoutInflater(), this);
        binding.RV.setAdapter(theCrewAdapter);
        binding.RV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        finalResultAdapter = new FinalResultAdapter(getLayoutInflater(), strings);
        binding.finalResultRV.setAdapter(finalResultAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        binding.finalResultRV.setLayoutManager(gridLayoutManager);
        getTheFinal();

        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(view -> CreatePopUpWindow());
    }

    private void CreatePopUpWindow() {

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;

        // Create the PopupWindow
        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setContentView(popupWindowBinding.getRoot());
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.border);
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.setFocusable(focusable);

// Set the content view of the PopupWindow
        popupWindow.setContentView(popupWindowBinding.getRoot());

// Set the soft input mode to adjust the PopupWindow position
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

// Create the SoftKeyboardStateWatcher
        SoftKeyboardStateWatcher softKeyboardStateWatcher = new SoftKeyboardStateWatcher(popupWindowBinding.getRoot());

// Set up the SoftKeyboardStateWatcher to update the position of the PopupWindow
        softKeyboardStateWatcher.addSoftKeyboardStateListener(new SoftKeyboardStateWatcher.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeight) {
                popupWindow.setHeight(keyboardHeight);
            }

            @Override
            public void onSoftKeyboardClosed() {
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });

// Show the PopupWindow
        popupWindow.showAtLocation(popupWindowBinding.getRoot(), Gravity.CENTER, 0, 0);
        popupWindowBinding.Nescafe.setOnCheckedChangeListener((compoundButton, b) -> popupWindowBinding.Coffee.setClickable(!b));
        popupWindowBinding.Coffee.setOnCheckedChangeListener((compoundButton, b) -> popupWindowBinding.Nescafe.setClickable(!b));
        popupWindowBinding.Canderel.setOnCheckedChangeListener((compoundButton, b) -> {
            popupWindowBinding.Sugar.setClickable(!b);
            popupWindowBinding.Nestle.setClickable(!b);
            popupWindowBinding.black.setClickable(!b);
            popupWindowBinding.coffeeMate.setClickable(!b);
        });
        popupWindowBinding.Sugar.setOnCheckedChangeListener((compoundButton, b) -> {
            popupWindowBinding.Nestle.setClickable(!b);
            popupWindowBinding.Canderel.setClickable(!b);
            popupWindowBinding.black.setClickable(!b);
            popupWindowBinding.coffeeMate.setClickable(!b);
        });
        popupWindowBinding.Nestle.setOnCheckedChangeListener((compoundButton, b) -> {
            popupWindowBinding.Canderel.setClickable(!b);
            popupWindowBinding.Sugar.setClickable(!b);
            popupWindowBinding.black.setClickable(!b);
            popupWindowBinding.coffeeMate.setClickable(!b);
        });
        popupWindowBinding.black.setOnCheckedChangeListener((compoundButton, b) -> {
            popupWindowBinding.Canderel.setClickable(!b);
            popupWindowBinding.Sugar.setClickable(!b);
            popupWindowBinding.Nestle.setClickable(!b);
            popupWindowBinding.coffeeMate.setClickable(!b);
        });
        popupWindowBinding.coffeeMate.setOnCheckedChangeListener((compoundButton, b) -> {
            popupWindowBinding.Canderel.setClickable(!b);
            popupWindowBinding.Sugar.setClickable(!b);
            popupWindowBinding.Nestle.setClickable(!b);
            popupWindowBinding.black.setClickable(!b);
        });
        popupWindowBinding.other.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                popupWindowBinding.otherTv.setVisibility(View.VISIBLE);
            }
            if (!b) {
                popupWindowBinding.otherTv.setVisibility(View.INVISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(popupWindowBinding.getRoot().getWindowToken(), 0);

            }
        });
        popupWindowBinding.oK.setOnClickListener(view -> {
            String otherText = "";
            boolean nescafe, coffee, canderel, sugar, nestle, other, black, coffeeMate;
            boolean isNameNotEmpty = !popupWindowBinding.nameText.getText().toString().isEmpty();
            boolean isOtherNotEmpty = !popupWindowBinding.otherTv.getText().toString().isEmpty();
            boolean isNescafeChecked = popupWindowBinding.Nescafe.isChecked();
            boolean isCoffeeChecked = popupWindowBinding.Coffee.isChecked();
            boolean isOtherChecked = popupWindowBinding.other.isChecked();
            boolean isNestleChecked = popupWindowBinding.Nestle.isChecked();
            boolean isCanderelChecked = popupWindowBinding.Canderel.isChecked();
            boolean isSugarChecked = popupWindowBinding.Sugar.isChecked();
            boolean isBlackChecked = popupWindowBinding.black.isChecked();
            boolean isCoffeeMateChecked = popupWindowBinding.coffeeMate.isChecked();

            if (isNameNotEmpty) {
                if (isNescafeChecked || isCoffeeChecked || (isOtherChecked && isOtherNotEmpty)) {
                    if (isNestleChecked || isCanderelChecked || isSugarChecked || isBlackChecked || isCoffeeMateChecked || ((!isOtherChecked || isOtherNotEmpty) && !isNescafeChecked && !isCoffeeChecked)) {
                        String name = String.valueOf(popupWindowBinding.nameText.getText());
                        nescafe = popupWindowBinding.Nescafe.isChecked();
                        coffee = popupWindowBinding.Coffee.isChecked();
                        canderel = popupWindowBinding.Canderel.isChecked();
                        sugar = popupWindowBinding.Sugar.isChecked();
                        nestle = popupWindowBinding.Nestle.isChecked();
                        other = popupWindowBinding.other.isChecked();
                        black = popupWindowBinding.black.isChecked();
                        coffeeMate = popupWindowBinding.coffeeMate.isChecked();
                        otherText = popupWindowBinding.otherTv.getText().toString();
                        TheCrew theCrew = new TheCrew(name, nescafe, coffee, canderel, sugar, nestle, other, black, coffeeMate);
                        if (isNescafeChecked || isCoffeeChecked){
                            names.add(theCrew);
                        }
                        if (isOtherNotEmpty) {
                            TheCrew theCrew2 = new TheCrew(name, otherText);
                            strings.add(theCrew2);
                        }

                        orderFinalCount(names);
                        getTheFinal();

                        finalResultAdapter.notifyDataSetChanged();
                        theCrewAdapter.notifyDataSetChanged();

                        popupWindowBinding.nameText.setText(null);
                        popupWindowBinding.otherTv.setText(null);
                        popupWindowBinding.Nescafe.setChecked(false);
                        popupWindowBinding.Coffee.setChecked(false);
                        popupWindowBinding.Canderel.setChecked(false);
                        popupWindowBinding.Sugar.setChecked(false);
                        popupWindowBinding.Nestle.setChecked(false);
                        popupWindowBinding.black.setChecked(false);
                        popupWindowBinding.coffeeMate.setChecked(false);
                        popupWindowBinding.other.setChecked(false);
                        popupWindow.dismiss();
                    } else {
                        Toast.makeText(this, "Choose Your Order", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Choose Your Order", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Type Your Name in The Name Box", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void orderFinalCount(ArrayList<TheCrew> theCrews) {

        nescafeCount = 0;
        nCanderelCount = 0;
        nSugarCount = 0;
        nNestleCount = 0;
        nBlackCount = 0;
        nCoffeeMateCount = 0;
        coffeeCount = 0;
        cCanderelCount = 0;
        cSugarCount = 0;
        cNestleCount = 0;
        cBlackCount = 0;
        cCoffeeMateCount = 0;

        for (int i = 0; i < theCrews.size(); i++) {
            boolean nescafe = theCrews.get(i).nescafe; // Assuming your ArrayList contains objects with a "CheckBox" property
            boolean coffee = theCrews.get(i).coffee;
            boolean canderel = theCrews.get(i).canderel;
            boolean sugar = theCrews.get(i).sugar;
            boolean nestle = theCrews.get(i).nestle;
            boolean black = theCrews.get(i).black;
            boolean coffeeMate = theCrews.get(i).coffeeMate;

            if (nescafe) {
                nescafeCount = nescafeCount + 1;
                if (canderel) {
                    nCanderelCount = nCanderelCount + 1;
                }
                if (sugar) {
                    nSugarCount = nSugarCount + 1;
                }
                if (nestle) {
                    nNestleCount = nNestleCount + 1;
                }
                if (black) {
                    nBlackCount = nBlackCount + 1;
                }
                if (coffeeMate) {
                    nCoffeeMateCount = nCoffeeMateCount + 1;
                }
            }
            if (coffee) {
                coffeeCount = coffeeCount + 1;
                if (canderel) {
                    cCanderelCount = cCanderelCount + 1;
                }
                if (sugar) {
                    cSugarCount = cSugarCount + 1;
                }
                if (nestle) {
                    cNestleCount = cNestleCount + 1;
                }
                if (black) {
                    cBlackCount = cBlackCount + 1;
                }
                if (coffeeMate) {
                    cCoffeeMateCount = cCoffeeMateCount + 1;
                }
            }
        }
    }

    public void getTheFinal() {
        binding.nescafeFRTV.setText(nescafeCount + ": Nescafe");
        binding.canderelFRTV.setText(nCanderelCount + ": with Canderel");
        binding.blackFRTV.setText(nBlackCount + ": Black");
        binding.nestleFRTV.setText(nSugarCount + ": with Sugar,");
        binding.sugarFRTV.setText(nNestleCount + ": with Nestle");
        binding.coffeeMateFRTV.setText(nCoffeeMateCount + ": No Sugar");

        binding.coffeeFRTV.setText(coffeeCount + ": Coffee");
        binding.canderelFRTV2.setText(cCanderelCount + ": with Canderel");
        binding.blackFRTV2.setText(cBlackCount + ": With nothing");
        binding.nestleFRTV2.setText(cSugarCount + ": with Sugar,");
        binding.sugarFRTV2.setText(cNestleCount + ": with Nestle");
        binding.coffeeMate2FRTV.setText(cCoffeeMateCount + ": No Sugar");
    }

    @Override
    public void onItemUpdated() {
        orderFinalCount(names);
        getTheFinal();
    }
}

