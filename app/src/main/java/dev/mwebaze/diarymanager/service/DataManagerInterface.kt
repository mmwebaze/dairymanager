package dev.mwebaze.diarymanager.service

import dev.mwebaze.diarymanager.model.DiaryCow

interface DataManagerInterface {
    fun getMilkingAnimals() : ArrayList<DiaryCow>
}