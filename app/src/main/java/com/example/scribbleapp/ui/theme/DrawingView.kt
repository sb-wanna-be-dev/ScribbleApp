import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var path = Path()
    private var paintBrush = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        strokeWidth = 10f
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val paths = mutableListOf<Pair<Path, Paint>>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw all previous paths
        paths.forEach { (path, paint) ->
            canvas.drawPath(path, paint)
        }

        // Draw current path
        canvas.drawPath(path, paintBrush)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }
            MotionEvent.ACTION_UP -> {
                paths.add(Pair(path, Paint(paintBrush)))
                path = Path()
            }
            else -> return false
        }

        invalidate()
        return true
    }

    fun changeColor(color: Int) {
        paintBrush.color = color
    }

    fun changeBrushSize(size: Float) {
        paintBrush.strokeWidth = size
    }

    fun clearCanvas() {
        paths.clear()
        path.reset()
        invalidate()
    }
}