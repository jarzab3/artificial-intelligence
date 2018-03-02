//public class tspLex {
//
//
//    int cities[];
//
//    int totalCities = 10;
//
//    int order[];
//
//    int totalPermutations;
//    int count = 0;
//
//
//    int recordDistance;
//    int bestEver;
//
//
//
//    int d = Utils.calculateTotalDistance(cities);
//
//
//        recordDistance = d;
//        bestEver = order.slice();
//
//        totalPermutations = factorial(totalCities);
//        console.log(totalPermutations);
//
//    }
//
//    function draw() {
//        background(0);
//        //frameRate(5);
//        fill(255);
//        for (int i = 0; i < cities.length; i++) {
//            ellipse(cities[i].x, cities[i].y, 8, 8);
//        }
//
//        stroke(255, 0, 255);
//        strokeWeight(4);
//        noFill();
//        beginShape();
//        for (int i = 0; i < order.length; i++) {
//            int n = bestEver[i];
//            vertex(cities[n].x, cities[n].y);
//        }
//        endShape();
//
//
//        translate(0, height / 2);
//        stroke(255);
//        strokeWeight(1);
//        noFill();
//        beginShape();
//        for (int i = 0; i < order.length; i++) {
//            int n = order[i];
//            vertex(cities[n].x, cities[n].y);
//        }
//        endShape();
//
//
//
//        int d = calcDistance(cities, order);
//        if (d < recordDistance) {
//            recordDistance = d;
//            bestEver = order.slice();
//        }
//
//        textSize(32);
//        // int s = '';
//        // for (var i = 0; i < order.length; i++) {
//        //   s += order[i];
//        // }
//        fill(255);
//        int percent = 100 * (count / totalPermutations);
//        text(nf(percent, 0, 2) + "% completed", 20, height / 2 - 50);
//
//        nextOrder();
//
//
//    }
//
//    function swap(a, i, j) {
//        int temp = a[i];
//        a[i] = a[j];
//        a[j] = temp;
//    }
//
//
//    function calcDistance(points, order) {
//        int sum = 0;
//        for (int i = 0; i < order.length - 1; i++) {
//            int cityAIndex = order[i];
//            int cityA = points[cityAIndex];
//            int cityBIndex = order[i + 1];
//            int cityB = points[cityBIndex];
//            int d = dist(cityA.x, cityA.y, cityB.x, cityB.y);
//            sum += d;
//        }
//        return sum;
//    }
//
//// This is my lexical order algorithm
//
//    function nextOrder() {
//        count++;
//
//        // STEP 1 of the algorithm
//        // https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering
//        int largestI = -1;
//        for (int i = 0; i < order.length - 1; i++) {
//            if (order[i] < order[i + 1]) {
//                largestI = i;
//            }
//        }
//        if (largestI == -1) {
//            noLoop();
//            console.log('finished');
//        }
//
//        // STEP 2
//        int largestJ = -1;
//        for (int j = 0; j < order.length; j++) {
//            if (order[largestI] < order[j]) {
//                largestJ = j;
//            }
//        }
//
//        // STEP 3
//        swap(order, largestI, largestJ);
//
//        // STEP 4: reverse from largestI + 1 to the end
//        int endArray = order.splice(largestI + 1);
//        endArray.reverse();
//        order = order.concat(endArray);
//    }
//
//    function factorial(n) {
//        if (n == 1) {
//            return 1;
//        } else {
//            return n * factorial(n - 1);
//        }
//    }
//
//
//
//}
