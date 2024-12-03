package com.example.myfirstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.Barrier
import com.example.myfirstcomposeapp.ui.theme.MyFirstComposeAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFirstComposeAppTheme {
                MyBox()
                MyComplexLayout()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


//@Preview
@Composable
fun MySuperText() {
    Text(
        text = "Soy Sema !!",
        modifier = Modifier
            .width(200.dp)
            .background(Color.Yellow)
            .padding(vertical = 30.dp, horizontal = 15.dp)
            )
}
@Preview(showBackground = true)
@Composable
fun MyPreview() {
    MyFirstComposeAppTheme {
        //MyBox()
        //MyColumn()
        //MyRow()
        //GuieLineExample()
        //BarrierExample()
        ChainExample()
    }
}
@Composable
fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart) {
        Box(modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .background(Color.Cyan)
            .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center) {
            Text("Esto es un EJEMPLO", fontSize = 70.sp)
        }
    }
}
@Composable
fun MyColumn() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Ejemplo1", modifier = Modifier.background(Color.Red).weight(1f))
        Text("Ejemplo2", modifier = Modifier.background(Color.Black).weight(1f))
        Text("Ejemplo3", modifier = Modifier.background(Color.Cyan).weight(1f))
        Text("Ejemplo4", modifier = Modifier.background(Color.Green).weight(1f))
    }
}
@Composable
fun MyRow() {
    Row(modifier = Modifier.fillMaxSize()
        .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Ejemplo1", modifier = Modifier.background(Color.Red).size(100.dp))
        Text("Ejemplo2", modifier = Modifier.background(Color.Black).size(100.dp))
        Text("Ejemplo3", modifier = Modifier.background(Color.Cyan).size(100.dp))
        Text("Ejemplo4", modifier = Modifier.background(Color.Green).size(100.dp))
        Text("Ejemplo1", modifier = Modifier.background(Color.Red).size(100.dp))
        Text("Ejemplo2", modifier = Modifier.background(Color.Black).size(100.dp))
        Text("Ejemplo3", modifier = Modifier.background(Color.Cyan).size(100.dp))
        Text("Ejemplo4", modifier = Modifier.background(Color.Green).size(100.dp))
        Text("Ejemplo1", modifier = Modifier.background(Color.Red).size(100.dp))
        Text("Ejemplo2", modifier = Modifier.background(Color.Black).size(100.dp))
        Text("Ejemplo3", modifier = Modifier.background(Color.Cyan).size(100.dp))
        Text("Ejemplo4", modifier = Modifier.background(Color.Green).size(100.dp))
    }
}
@Composable
fun MyComplexLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Cyan))
            Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Magenta),
                contentAlignment = Alignment.Center
            ) {
                Text("Hola clase!")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Gray)
        )
    }
}

@Composable
fun GuieLineExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxYellow, boxMagenta) = createRefs()
        val glTop = createGuidelineFromTop(0.15f)
        val glBottom = createGuidelineFromBottom(300.dp)
        val glStart = createGuidelineFromStart(0.25f)
        val glEnd = createGuidelineFromEnd(0.15f)
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(ref = boxRed) {
                top.linkTo(glTop)
                start.linkTo(glStart)
                end.linkTo(glEnd)
                bottom.linkTo(glBottom)
            }
        )
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                bottom.linkTo(boxRed.top)
                start.linkTo(glEnd)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                end.linkTo(boxBlue.start)
                top.linkTo(boxBlue.top)
                bottom.linkTo(boxBlue.bottom)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                start.linkTo(boxRed.end)
                top.linkTo(boxBlue.top)
                bottom.linkTo(boxRed.bottom)
            })
    }
}

@Composable
fun ConstraintExample(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxYellow, boxMagenta) = createRefs()
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(ref = boxRed){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue){
                bottom.linkTo(boxRed.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow){
                end.linkTo(boxBlue.start)
                top.linkTo(boxBlue.top)
                bottom.linkTo(boxBlue.bottom)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta){
                start.linkTo(boxRed.end)
                top.linkTo(boxBlue.top)
                bottom.linkTo(boxRed.bottom)
            })
    }
}
//@Preview(showBackground = true)
/*@Composable
fun BarrierExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
       val (boxRed, boxBlue, boxYellow, boxMagenta) = createRefs()
        //val bTop = createTopBarrier(0.15f)
        val bBottom = createBottomBarrier(boxRed, boxBlue)
        //val bStart= createStartBarrier(0.25f)
        val bEnd = createEndBarrier(boxRed, boxBlue)
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(ref = boxRed) {
                top.linkTo(glTop)
                start.linkTo(glStart)
                end.linkTo(glEnd)
                bottom.linkTo(glBottom)
            }
        )
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                bottom.linkTo(boxRed.top)
                start.linkTo(glEnd)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                end.linkTo(boxBlue.start)
                top.linkTo(boxBlue.top)
                bottom.linkTo(boxBlue.bottom)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                start.linkTo(boxRed.end)
                top.linkTo(boxBlue.top)
                bottom.linkTo(boxRed.bottom)
            })
    }
}*/
@Preview
@Composable
fun ChainExample(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxYellow, boxMagenta) = createRefs()
        val size = 70.dp
        Box(modifier = Modifier
            .size(size)
            .background(Color.Red)
            .constrainAs(ref = boxRed){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
        Box(modifier = Modifier
            .size(size)
            .background(Color.Blue)
            .constrainAs(boxBlue){
                bottom.linkTo(boxRed.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(size)
            .background(Color.Yellow)
            .constrainAs(boxYellow){
                end.linkTo(boxBlue.start)
                top.linkTo(boxBlue.top)
                bottom.linkTo(boxBlue.bottom)
            })
        Box(modifier = Modifier
            .size(size)
            .background(Color.Magenta)
            .constrainAs(boxMagenta){
                start.linkTo(boxYellow.end)
                top.linkTo(boxBlue.top)
                bottom.linkTo(boxRed.bottom)
            })
        createHorizontalChain(boxBlue, boxBlue, boxYellow, boxMagenta,
        chainStyle = ChainStyle.Spread)
    }
}


