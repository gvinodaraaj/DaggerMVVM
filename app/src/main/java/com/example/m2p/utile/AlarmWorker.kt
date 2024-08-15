import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class AlarmWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // Perform your task here, like showing a notification
        Log.d("AlarmWorker", "Alarm triggered!")

        // Return success, failure, or retry depending on the outcome
        return Result.success()
    }
}
