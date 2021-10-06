package ru.korolevss.compose_general.common

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ErrorDialog(
    @StringRes errorMessage: Int,
    @StringRes retryMessage: Int,
    onErrorDismiss: () -> Unit,
    onRetry: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.padding(20.dp),
        onDismissRequest = { onErrorDismiss() },
        title = {
            Text(
                text = stringResource(id = errorMessage),
                style = MaterialTheme.typography.h6
            )
        },
        confirmButton = {
            Text(
                text = stringResource(id = retryMessage),
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(15.dp)
                    .clickable { onRetry() }
            )
        },
        dismissButton = {
            Text(
                text = stringResource(id = android.R.string.cancel),
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .padding(15.dp)
                    .clickable { onErrorDismiss() }
            )
        }
    )
}