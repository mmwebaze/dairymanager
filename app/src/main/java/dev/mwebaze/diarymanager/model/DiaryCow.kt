package dev.mwebaze.diarymanager.model

data class DiaryCow (val tagId: Int, val name: String) {
    override fun toString(): String {

        return this.name;
    }
}