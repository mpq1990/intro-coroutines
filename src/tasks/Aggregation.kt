package tasks

import contributors.User

/*
TODO: Write aggregation code.

 In the initial list each user is present several times, once for each
 repository he or she contributed to.
 Merge duplications: each user should be present only once in the resulting list
 with the total value of contributions for all the repositories.
 Users should be sorted in a descending order by their contributions.

 The corresponding test can be found in test/tasks/AggregationKtTest.kt.
 You can use 'Navigate | Test' menu action (note the shortcut) to navigate to the test.
*/
fun List<User>.aggregate(): List<User> {
    return this.groupingBy { it.login }.aggregateTo(mutableMapOf()) { key, accumulator: Int?, element, first ->
        if (first) {
            element.contributions
        } else {
            accumulator?.plus(element.contributions)
        }
    }.map {
        val contributions = it.value ?: 0
        User(it.key, contributions)
    }.sortedByDescending { it.contributions }

//    return this.groupBy { it.login }.map { (login, group) -> User(login, group.sumBy { it.contributions }) }
//        .sortedByDescending { it.contributions }
}
