package dev.mwebaze.diarymanager.service

import dev.mwebaze.diarymanager.model.DiaryCow
import java.util.*
import kotlin.collections.ArrayList

class DataManager : DataManagerInterface {
    override fun getMilkingAnimals(): ArrayList<DiaryCow> {

        val milkingList = ArrayList<DiaryCow>()
        for (i in 1..5) {
            val random = Random()
            val ranValue = random.nextInt(10 - 1)
            milkingList.add(DiaryCow(ranValue, "KY-"+ranValue))
        }

        return milkingList
    }
}