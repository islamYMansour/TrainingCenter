//package com.example.islam.trainingcenter.app.Module.Activites;//package com.example.islam.trainingcentermanagment.app.Module.Activites;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.daimajia.slider.library.Animations.DescriptionAnimation;
//import com.daimajia.slider.library.SliderLayout;
//import com.daimajia.slider.library.SliderTypes.BaseSliderView;
//import com.daimajia.slider.library.SliderTypes.TextSliderView;
//import com.daimajia.slider.library.Tricks.ViewPagerEx;
//
//import com.example.islam.trainingcentermanagment.DB.DBFacade;
//import com.example.islam.trainingcentermanagment.DB.SQLiteHelper;
//import com.example.islam.trainingcentermanagment.R;
//import com.example.islam.trainingcentermanagment.Utilites.Utils;
//import com.facebook.drawee.view.SimpleDraweeView;
//
//import java.util.HashMap;
//
//public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
//
//    private SliderLayout mDemoSlider;
//    AlertDialog.Builder dialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_navigation);
//        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        ImageButton openNavigate = (ImageButton) findViewById(R.id.open_navigation);
//        Button logout = (Button) findViewById(R.id.logout);
//        dialog = new AlertDialog.Builder(this);
//
//
//        openNavigate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawerLayout.openDrawer(GravityCompat.END);
//            }
//        });
//
//
//        View header = navigationView.getHeaderView(0);
//        SimpleDraweeView userPic = header.findViewById(R.id.user_pic);
//        TextView userName = header.findViewById(R.id.user_name);
//
//        String phone = Utils.getUser(this);
//        DBFacade db = new DBFacade(this);
//        db.open();
//        final Cursor cursor = db.getAminByCoulmn(SQLiteHelper.ADMIN_PHONE_COULMN, SQLiteHelper.ADMIN_TABLE,phone);
//        cursor.moveToFirst();
//        String nameString = cursor.getString(cursor.getColumnIndex(SQLiteHelper.ADMIN_NAME_COLUMN));
//        String ImagePath = cursor.getString(cursor.getColumnIndex(SQLiteHelper.ADMIN_IMAGE_PATH));
//        userPic.setImageURI(Uri.parse(ImagePath));
//        userName.setText(nameString);
//        db.close();
//
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.manage_ads:
//                        break;
//                    case R.id.manage_courses:
//                        startActivity(new Intent(MainActivity.this,ManageCourse.class));
//                        break;
//                    case R.id.call_us:
//                        break;
//                    case R.id.about_app:
//                        break;
//                }
//
//                return true;
//            }
//        });
//
//
//        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
//        final HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Hannibal", R.drawable.background);
//        file_maps.put("Big Bang Theory", R.drawable.logo);
//        file_maps.put("House of Cards", R.drawable.background);
//        file_maps.put("Game of Thrones", R.drawable.logo);
//
//        for (String name : file_maps.keySet()) {
//            TextSliderView textSliderView = new TextSliderView(this);
//            // initialize a SliderLayout
//            textSliderView
//                    .description(name)
//                    .image(file_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                    .setOnSliderClickListener(this);
//
//            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra", name);
//
//            mDemoSlider.addSlider(textSliderView);
//        }
//        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
//        mDemoSlider.setDuration(4000);
//
//
////
////        final ArrayList list = new ArrayList();
////        list.add(new Admins(0));
////        list.add(new Admins(1));
////        list.add(new Admins(2));
////        list.add(new Admins(33));
////
////
////        RealmHelper.excuteTransaction(new Realm.Transaction() {
////            @Override
////            public void execute(Realm realm) {
////                realm.copyToRealmOrUpdate(list);
////            }
////        });
////
////        getFromDataBase();
////    }
////
////    private List<Admins> getFromDataBase() {
////
////        RealmResults<Admins> result = RealmHelper.sRealm.where(Admins.class).findAll();
////        if (result != null && !result.isEmpty()) {
////            for (int i =0; i<result.toArray().length;i++){
////               Log.d("resultrealm", result.toArray()[i]+"");
////            }
////        }
////
////        return null;
//
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dialog.setIcon(R.drawable.cancel)
//                        .setTitle(R.string.logout)
//                        .setMessage(R.string.logout_assure)
//                        .setNegativeButton(R.string.cancel, null)
//                        .setPositiveButton(R.string.yes_logout, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Utils.removeUser(MainActivity.this);
//                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                                finish();
//                            }
//                        }).show();
//
//            }
//        });
//    }
//
//    @Override
//    public void onSliderClick(BaseSliderView slider) {
//
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//}
