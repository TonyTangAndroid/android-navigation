package com.example.android.codelabs.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class FlowStepFragment extends Fragment {

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    FlowStepFragmentArgs safeArgs = FlowStepFragmentArgs.fromBundle(requireArguments());
    int flowStepNumber = safeArgs.getFlowStepNumber();
    if (2 == flowStepNumber) {
      return inflater.inflate(R.layout.flow_step_two_fragment, container, false);
    }
    return inflater.inflate(R.layout.flow_step_one_fragment, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.findViewById(R.id.next_button)
        .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.next_action));
  }
}
