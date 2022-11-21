package com.janettha.jetpackcompose.themoviedb.core.util.extensions

import com.xwray.groupie.*
import java.lang.ClassCastException

fun GroupAdapter<GroupieViewHolder>.submit(
    data: List<Group>,
    refresh: Boolean = true,
    animate: Boolean = false
) {
    if (refresh) {
        clear()
        addAll(data)
        return
    }

    if (groupCount > 0 || itemCount > 0) {
        update(data, animate)
    } else {
        addAll(data)
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Item<*>> GroupieAdapter.find(predicate: (T) -> Boolean): T? {
    if (itemCount == 0) return null

    for (i in 0 until itemCount) {
        val item = getItem(i) as? T

        item ?: return null

        try {
            if (predicate(item)) return item
        } catch (_: ClassCastException) {
            return null
        }
    }

    return null
}

fun <T : Item<*>> GroupieAdapter.removeIfExist(predicate: (T) -> Boolean): Boolean {
    val mItem = find(predicate)

    mItem ?: return false

    remove(mItem); return true
}