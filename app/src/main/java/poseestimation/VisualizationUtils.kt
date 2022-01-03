package org.tensorflow.lite.examples.poseestimation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import org.tensorflow.lite.examples.poseestimation.data.BodyPart
import org.tensorflow.lite.examples.poseestimation.data.Person
import kotlin.math.*

object VisualizationUtils {
    /** Radius of circle used to draw keypoints.  */
    private const val CIRCLE_RADIUS = 6f

    /** Width of line used to connected two keypoints.  */
    private const val LINE_WIDTH = 4f

    /** Pair of keypoints to draw lines between.  */
    private val bodyJoints = listOf(
        Pair(BodyPart.NOSE, BodyPart.LEFT_EYE),
        Pair(BodyPart.NOSE, BodyPart.RIGHT_EYE),
        Pair(BodyPart.LEFT_EYE, BodyPart.LEFT_EAR),
        Pair(BodyPart.RIGHT_EYE, BodyPart.RIGHT_EAR),
        //Pair(BodyPart.NOSE, BodyPart.LEFT_SHOULDER),
        //Pair(BodyPart.NOSE, BodyPart.RIGHT_SHOULDER),
        Pair(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_ELBOW),
        Pair(BodyPart.LEFT_ELBOW, BodyPart.LEFT_WRIST),
        Pair(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_ELBOW),
        Pair(BodyPart.RIGHT_ELBOW, BodyPart.RIGHT_WRIST),
        //Pair(BodyPart.LEFT_SHOULDER, BodyPart.RIGHT_SHOULDER),
        Pair(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_HIP),
        Pair(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_HIP),
        //Pair(BodyPart.LEFT_HIP, BodyPart.RIGHT_HIP),`
        Pair(BodyPart.LEFT_HIP, BodyPart.LEFT_KNEE),
        Pair(BodyPart.LEFT_KNEE, BodyPart.LEFT_ANKLE),
        Pair(BodyPart.RIGHT_HIP, BodyPart.RIGHT_KNEE),
        Pair(BodyPart.RIGHT_KNEE, BodyPart.RIGHT_ANKLE)
    )
    private val bodyAngles = listOf(
        Pair(Pair(BodyPart.LEFT_ELBOW, BodyPart.LEFT_WRIST),
            Pair(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_ELBOW)),
        Pair(Pair(BodyPart.RIGHT_ELBOW, BodyPart.RIGHT_WRIST),
            Pair(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_ELBOW)),
        Pair(Pair(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_ELBOW),
            Pair(BodyPart.LEFT_HIP, BodyPart.LEFT_SHOULDER)),
        Pair(Pair(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_ELBOW),
            Pair(BodyPart.RIGHT_HIP, BodyPart.RIGHT_SHOULDER)),
        Pair(Pair(BodyPart.LEFT_HIP, BodyPart.LEFT_KNEE),
            Pair(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_HIP)),
        Pair(Pair(BodyPart.RIGHT_HIP, BodyPart.RIGHT_KNEE),
            Pair(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_HIP)),
        Pair(Pair(BodyPart.LEFT_KNEE, BodyPart.LEFT_ANKLE),
            Pair(BodyPart.LEFT_HIP, BodyPart.LEFT_KNEE)),
        Pair(Pair(BodyPart.RIGHT_KNEE, BodyPart.RIGHT_ANKLE),
            Pair(BodyPart.RIGHT_HIP, BodyPart.RIGHT_KNEE))
    )


    private var angles = arrayOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
    private val perfectPoseWarriorRight = arrayOf(0.0,0.0,0.0,0.0,0.0,85.0,90.0,170.0,170.0,0.0,0.0,135.0,100.0,170.0,120.0)
    private val perfectPoseTree = arrayOf(0.0)
    private val perfectPoseDownwardDog = arrayOf(0.0,0.0,0.0,0.0,0.0,170.0,170.0,170.0,170.0,170.0,170.0,90.0,90.0,175.0,175.0)
    private val perfectPosePlank = arrayOf(0.0,0.0,0.0,0.0,0.0,60.0,60.0,170.0,170.0,0.0,0.0,170.0,180.0,175.0,175.0)

    private var currentPose = perfectPosePlank

    // Draw line and point indicate body pose

    fun drawBodyKeypoints(input: Bitmap, person: Person): Bitmap {
        var totalError: Double = 0.0
         val pText = Paint()
        pText.color = Color.RED
        val paintCircle = Paint().apply {
            strokeWidth = CIRCLE_RADIUS
            color = Color.RED
            style = Paint.Style.FILL
        }
        val paintLine = Paint().apply {
            strokeWidth = LINE_WIDTH
            color = Color.RED
            style = Paint.Style.FILL
        }


        val output = input.copy(Bitmap.Config.ARGB_8888,true)
        val originalSizeCanvas = Canvas(output)

        bodyJoints.forEach {
            val pointA = person.keyPoints[it.first.position].coordinate
            val pointB = person.keyPoints[it.second.position].coordinate
            originalSizeCanvas.drawLine(pointA.x, pointA.y, pointB.x, pointB.y, paintLine)
        }
        bodyAngles.forEach{
            val pointA = person.keyPoints[it.first.first.position].coordinate
            val pointB = person.keyPoints[it.first.second.position].coordinate
            val pointC = person.keyPoints[it.second.first.position].coordinate
            val pointD = person.keyPoints[it.second.second.position].coordinate

            val vec = Pair((pointA.x-pointB.x), (pointA.y-pointB.y))
            val vec2 = Pair((pointC.x-pointD.x), (pointC.y-pointD.y))

            val numerator = (vec.first*vec2.first) + (vec.second*vec2.second)
            //vector a x coordinate * vector b x-coordinate + vector a y-coordinate * vector b y-coordinate
            val denominator1 = sqrt((vec.first*vec.first)+(vec.second*vec.second))
            val denominator2 = sqrt((vec2.first*vec2.first)+(vec2.second*vec2.second))
            //magnitude = root(x^2 + y^2)
            val denominator = denominator1*denominator2
            // multiply magnitude of vector a by b
            val angle = Math.toDegrees((acos(numerator/denominator)).toDouble())
            //find arccos of numerator/denominator and convert to degrees
            val number2digits:Double = 180.0 - String.format("%.2f", angle).toDouble()
            //originalSizeCanvas.drawText(number2digits.toString(),(pointA.x+10f),(pointA.y+10f),pText)
            angles[it.first.first.position] = number2digits
            var error = abs(((currentPose[it.first.first.position]- number2digits)/ currentPose[it.first.first.position]))

            if (error > 0.15){
                pText.textSize = 20f
                error  *= 100
                error = String.format("%.2f", error).toDouble()
                originalSizeCanvas.drawText("<: $error %",(pointA.x+10f),(pointA.y+10f),pText)
                totalError += error
            }
        }

        var totalPercentError = totalError/8
        if (totalError == 0.0){
            totalPercentError = 0.0
        }
        pText.textSize = 30f
        totalPercentError = String.format("%.2f", totalPercentError).toDouble()
        originalSizeCanvas.drawText("Total Error: $totalPercentError %",(130.0f),(30.0f),pText)
        //println("<" + angles[7] + ", " + angles[8] + ", " + angles[5] + ", " + angles[6] + ", " + angles[11] + ", " + angles[12] + ", " + angles[13] + ", " + angles[14] + ">")

        person.keyPoints.forEach { point ->
            originalSizeCanvas.drawCircle(
                point.coordinate.x,
                point.coordinate.y,
                CIRCLE_RADIUS,
                paintCircle
            )
        }
        return output
    }
}