package com.example.android.codelabs.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class HomeFragment extends Fragment {

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    return inflater.inflate(R.layout.home_fragment, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Button button = view.findViewById(R.id.navigate_destination_button);
    button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Navigation.findNavController(view).navigate(R.id.flow_step_one_dest, null);
          }
        });
    NavOptions.Builder navOptionsBuilder = new NavOptions.Builder();
    navOptionsBuilder.setEnterAnim(R.anim.slide_in_right);
    navOptionsBuilder.setExitAnim(R.anim.slide_out_left);
    navOptionsBuilder.setPopEnterAnim(R.anim.slide_in_left);
    navOptionsBuilder.setPopExitAnim(R.anim.slide_out_right);
    final NavOptions options = navOptionsBuilder.build();

    view.findViewById(R.id.navigate_destination_button)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.flow_step_one_dest, null, options);
              }
            });
    view.findViewById(R.id.navigate_action_button)
        .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.next_action, null));
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.main_menu, menu);
  }
}
