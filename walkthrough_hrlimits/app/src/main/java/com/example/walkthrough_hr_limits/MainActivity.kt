package com.example.walkthrough_hr_limits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.walkthrough_hr_limits.ui.theme.Walkthrough_hrlimitsTheme
import androidx.compose.ui.platform.LocalFocusManager



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Walkthrough_hrlimitsTheme {
                HeartRateLimits()
            }
        }
    }
}

@Composable
fun HeartRateLimits(hrViewModel: HrViewModel = viewModel()) {
    val focusManager = LocalFocusManager.current
    Column (
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = hrViewModel.ageInput,
            onValueChange = { hrViewModel.changeAgeInput(it) },
            label = {Text(text = stringResource(R.string.age))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions( onDone = {focusManager.clearFocus()} )
        )
        Text(
            text = stringResource(R.string.heart_rate_limits,hrViewModel.lower,hrViewModel.upper)
        )
    }
}
