package dev.mwebaze.dairymanager.service

import dev.mwebaze.dairymanager.model.DairyCow

interface DataManagerInterface {
    fun getMilkingAnimals() : ArrayList<DairyCow>
}