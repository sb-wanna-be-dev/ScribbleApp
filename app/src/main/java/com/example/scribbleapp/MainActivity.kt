import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.scribbleapp.R
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    private lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawingView)

        // Color selection buttons
        val blackBtn: Button = findViewById(R.id.blackColorBtn)
        val redBtn: Button = findViewById(R.id.redColorBtn)
        val blueBtn: Button = findViewById(R.id.blueColorBtn)

        // Brush size buttons
        val thinBtn: Button = findViewById(R.id.thinBrushBtn)
        val mediumBtn: Button = findViewById(R.id.mediumBrushBtn)
        val thickBtn: Button = findViewById(R.id.thickBrushBtn)

        // Clear button
        val clearBtn: Button = findViewById(R.id.clearBtn)

        // Color button listeners
        blackBtn.setOnClickListener {
            drawingView.changeColor(Color.BLACK)
        }
        redBtn.setOnClickListener {
            drawingView.changeColor(Color.RED)
        }
        blueBtn.setOnClickListener {
            drawingView.changeColor(Color.BLUE)
        }

        // Brush size listeners
        thinBtn.setOnClickListener {
            drawingView.changeBrushSize(5f)
        }
        mediumBtn.setOnClickListener {
            drawingView.changeBrushSize(10f)
        }
        thickBtn.setOnClickListener {
            drawingView.changeBrushSize(20f)
        }

        // Clear canvas
        clearBtn.setOnClickListener {
            drawingView.clearCanvas()
        }
    }
}