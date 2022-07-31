package com.example.instructorlsa.viewmodels.signs

import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class SignScreenViewModel(category: CategoryViewModel, sign: SignViewModel) {
    var category: CategoryViewModel
    var sign: SignViewModel

    init {
        this.category = category
        this.sign = sign
    }
}
