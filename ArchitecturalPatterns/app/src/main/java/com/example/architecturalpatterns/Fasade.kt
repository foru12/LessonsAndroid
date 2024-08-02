package com.example.architecturalpatterns

interface Product2 {
    fun use()
}

// Конкретные продукты
class ConcreteProductA : Product2 {
    override fun use() {
        println("Using ConcreteProductA")
    }
}

class ConcreteProductB : Product2 {
    override fun use() {
        println("Using ConcreteProductB")
    }
}

// Интерфейс фабрики
interface ProductFactory {
    fun createProduct(): Product2
}

// Конкретные фабрики
class ConcreteProductAFactory : ProductFactory {
    override fun createProduct(): Product2 {
        return ConcreteProductA()
    }
}

class ConcreteProductBFactory : ProductFactory {
    override fun createProduct(): Product2 {
        return ConcreteProductB()
    }
}