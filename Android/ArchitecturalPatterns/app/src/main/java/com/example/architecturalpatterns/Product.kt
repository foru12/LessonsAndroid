package com.example.architecturalpatterns

class Product(
    val name: String?,
    val price: Double?,
    val description: String?
) {


    class Builder {
        private var name: String? = null
        private var price: Double? = null
        private var description: String? = null

        fun setName(name: String) = apply { this.name = name }
        fun setPrice(price: Double) = apply { this.price = price }
        fun setDescription(description: String) = apply { this.description = description }

        fun build() = Product(name, price, description)
    }


}
