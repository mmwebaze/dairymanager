package dev.mwebaze.dairymanager.service

import dev.mwebaze.dairymanager.model.DairyCow
import java.util.*
import kotlin.collections.ArrayList

class DataManager : DataManagerInterface {
    override fun getMilkingAnimals(): ArrayList<DairyCow> {

        val milkingList = ArrayList<DairyCow>()
        for (i in 1..5) {
            val random = Random()
            val ranValue = random.nextInt(10 - 1)
            milkingList.add(DairyCow(ranValue, "KY-" + ranValue))
        }

        return milkingList
    }
}