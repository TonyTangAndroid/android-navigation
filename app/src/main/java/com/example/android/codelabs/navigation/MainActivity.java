package com.example.android.codelabs.navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
  private AppBarConfiguration appBarConfiguration;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.navigation_activity);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    NavHostFragment host =
        (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);

    // Set up Action Bar
    NavController navController = host.getNavController();

    appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

    // You should also remove the old appBarConfiguration setup above
    DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
    Set<Integer> topLevelDestinations = new HashSet<>();
    topLevelDestinations.add(R.id.home_dest);
    topLevelDestinations.add(R.id.deeplink_dest);
    if (null != drawerLayout) {
      appBarConfiguration =
          new AppBarConfiguration.Builder(topLevelDestinations)
              .setDrawerLayout(drawerLayout)
              .build();
    }

    setupActionBar(navController, appBarConfiguration);

    setupNavigationMenu(navController);

    setupBottomNavMenu(navController);
  }

  private void setupBottomNavMenu(NavController navController) {
    BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
    if (null != bottomNav) {
      NavigationUI.setupWithNavController(bottomNav, navController);
    }
  }

  private void setupNavigationMenu(NavController navController) {
    NavigationView sideNavView = findViewById(R.id.nav_view);
    if (null != sideNavView) {
      NavigationUI.setupWithNavController(sideNavView, navController);
    }
  }

  private void setupActionBar(NavController navController, AppBarConfiguration appBarConfig) {
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    boolean retValue = super.onCreateOptionsMenu(menu);
    NavigationView navigationView = findViewById(R.id.nav_view);
    // The NavigationView already has these same navigation items, so we only add
    // navigation items to the menu here if there isn't a NavigationView
    if (navigationView == null) {
      getMenuInflater().inflate(R.menu.overflow_menu, menu);
      return true;
    }
    return retValue;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return NavigationUI.onNavDestinationSelected(
            item, Navigation.findNavController(this, R.id.my_nav_host_fragment))
        || super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onSupportNavigateUp() {
    //        // Allows NavigationUI to support proper up navigation or the drawer layout
    //        // drawer menu, depending on the situation
    return NavigationUI.navigateUp(
        Navigation.findNavController(this, R.id.my_nav_host_fragment), appBarConfiguration);
  }
}
