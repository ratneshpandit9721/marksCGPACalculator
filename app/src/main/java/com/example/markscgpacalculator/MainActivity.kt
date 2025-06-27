package com.example.markscgpacalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import com.example.markscgpacalculator.ui.theme.MarksCGPACalculatorTheme
import com.example.markscgpacalculator.R
import com.example.markscgpacalculator.ui.theme.MarksCGPACalculatorTheme

data class Semester( val grade:Int, val credit: Int)
class MainActivity: ComponentActivity() {
    private var semesters:MutableList<Semester> = mutableListOf()



    private fun calculateGradePoints(grade: Int, credit: Int): Double {
        val gradePoint = when (grade) {
            in 90..100 -> 10.0
            in 80..89 -> 9.0
            in 70..79 -> 8.0
            in 60..69 -> 7.0
            in 50..59 -> 6.0
            in 40..49 -> 5.0
            else -> 0.0
        }
        return gradePoint * credit
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
             MarksCGPACalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->



                    var cgpa by remember { mutableDoubleStateOf(0.0)}
                    var grade1 by remember { mutableStateOf<Int?>(null) }
                    var credit1 by remember { mutableStateOf<Int?>(null) }
                    var grade2 by remember { mutableStateOf<Int?>(null) }
                    var credit2 by remember { mutableStateOf<Int?>(null) }
                    var grade3 by remember { mutableStateOf<Int?>(null) }
                    var credit3 by remember { mutableStateOf<Int?>(null) }
                    var grade4 by remember { mutableStateOf<Int?>(null) }
                    var credit4 by remember { mutableStateOf<Int?>(null) }
                    var grade5 by remember { mutableStateOf<Int?>(null) }
                    var credit5 by remember { mutableStateOf<Int?>(null) }
                    var grade6 by remember { mutableStateOf<Int?>(null) }
                    var credit6 by remember { mutableStateOf<Int?>(null) }
                    var grade7 by remember { mutableStateOf<Int?>(null) }
                    var credit7 by remember { mutableStateOf<Int?>(null) }

                    val scrollState = rememberScrollState()

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .verticalScroll(scrollState),
                    )



                    {

                        Text(

                            text = "CGPA Calculator \n Please enter the your marks out of 100",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color(0xFF000000),
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold))
                        )
                        Spacer(modifier = Modifier.padding(top = 15.dp))
                        SubjectText(subject = "Subject 1")
                        GradeTextField(grade1)  { grade1 = it }
                        Spacer8dp()
                        CreditTextField (credit1) {  credit1= it }
                        SubjectText(subject = "Subject 2")
                        GradeTextField (grade2) { grade2 = it }
                        Spacer8dp()
                        CreditTextField (credit2) {  credit2= it }
                        SubjectText(subject = "Subject 3")
                        GradeTextField (grade3) {grade3 = it}
                        Spacer8dp()
                        CreditTextField (credit3) {  credit3= it }
                        SubjectText(subject = "Subject 4")
                        GradeTextField (grade4) { grade4= it}
                        Spacer8dp()
                        CreditTextField (credit4) {  credit4= it }
                        SubjectText(subject = "Subject 5")
                        GradeTextField (grade5) { grade5= it}
                        Spacer8dp()
                        CreditTextField (credit5) {  credit5= it }
                        SubjectText(subject = "Subject 6")
                        GradeTextField (grade6) { grade6= it}
                        Spacer8dp()
                        CreditTextField (credit6) {  credit6= it }
                        SubjectText(subject = "Subject 7")
                        GradeTextField (grade7) { grade7= it}
                        Spacer8dp()
                        CreditTextField (credit7) {  credit7= it }
                        Spacer8dp()
                        Row {
                            Column {
                                Button(
                                     onClick = {
                                             semesters.clear()

                                             val inputs = listOf(
                                                 grade1 to credit1,
                                                 grade2 to credit2,
                                                 grade3 to credit3,
                                                 grade4 to credit4,
                                                 grade5 to credit5,
                                                 grade6 to credit6,
                                                 grade7 to credit7
                                             )

                                             for ((grade, credit) in inputs) {
                                                 if (grade != null && credit != null && credit > 0) {
                                                     semesters.add(Semester(grade, credit))
                                                 }
                                             }

                                             val totalCredit = semesters.sumOf { it.credit }
                                             val totalGradePoint = semesters.sumOf { calculateGradePoints(it.grade, it.credit) }
                                             cgpa = if (totalCredit > 0) {
                                                 totalGradePoint / totalCredit.toDouble()
                                             } else {
                                                 0.0
                                             }

                                             // Reset inputs
                                             grade1 = null; credit1 = null
                                             grade2 = null; credit2 = null
                                             grade3 = null; credit3 = null
                                             grade4 = null; credit4 = null
                                             grade5 = null; credit5 = null
                                             grade6 = null; credit6 = null
                                             grade7 = null; credit7 = null


                                     },
                                    colors = ButtonDefaults.buttonColors(Color(0xFFBEABE0)),
                                    shape = RoundedCornerShape(15.dp)
                                ) {
                                    Text(
                                        text = "Calculate CGPA",
                                        fontSize = 16.sp,
                                        color = Color.Black,
                                        fontFamily = FontFamily(
                                            Font(R.font.poppins_medium)
                                        )
                                    )
                                }
                                Surface(
                                    modifier = Modifier
                                        .width(160.dp)
                                        .wrapContentHeight(),
                                    color = Color(0xFF263238),
                                    shape = RoundedCornerShape(16.dp)
                                ) {

                                    Text(
                                        modifier = Modifier.padding(start = 10.dp),
                                        text  = "Your all time \n CGPA: %.4f".format(cgpa),
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                                        ),
                                        fontSize = 16.sp, color = Color(0xFFFFFFFF)
                                    )
                                }
                            }
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 60.dp),
                                color = Color(0xFF263238),
                                shape = RoundedCornerShape(16.dp)
                            )

                            {
                                Column {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 15.dp),
                                        text = "Previous Semester CGPA :",
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                                        ),
                                        fontSize = 16.sp, color = Color(0xFFFFFFFF)
                                    )

                                    if (semesters.isNotEmpty()){
                                        for (semester in semesters)
                                            Text(text = "Grade: ${semester.grade}  , Credit:${semester.credit} ", color = Color.White, fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 16.sp, modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                                    }
                                }

                            }
                        }
                    }

                }

            }
        }
    }

}


@Composable
fun Spacer8dp() {
    Spacer(modifier = Modifier.padding(top = 8.dp))
}

@Composable
fun SubjectText(subject: String) {

    Text(
        text = subject,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Left,
        fontSize = 20.sp,
        color = Color(0xFF000000),
        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily(Font(R.font.poppins_medium))
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeTextField(grade: Int?, onValueChange: (Int?) -> Unit) {
    TextField(
        value = grade?.toString()?:"", onValueChange = { text ->
            onValueChange(text.toIntOrNull())
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = { Text(text = "Enter Mark", color = Color.White, fontSize = 12.sp) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color(0xFF7D8CCED),
            focusedContainerColor = Color(0xFF7D8CCED),
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
    )

}

@Composable
fun CreditTextField(credit: Int?, onValueChange: (Int?) -> Unit) {
    TextField(
        value = credit?.toString()?:"", onValueChange = { text ->
            onValueChange(text.toIntOrNull()) },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = {
            Text(
                text = "Enter Credit",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 12.sp
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color(0xFF7E57C2),
            focusedContainerColor = Color(0xFF7E57C2),
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
    )

}



