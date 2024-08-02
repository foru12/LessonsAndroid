package com.example.architecturalpatterns


// Реализация интерфейса
class ServiceImpl : Service {
    override fun performAction() {
        println("Performing action in ServiceImpl")
    }
}

// Класс, в который будут внедряться зависимости
class Client(private val service: Service) {
    fun doSomething() {
        service.performAction()
    }
}
// Интерфейс для зависимой компоненты
interface Service {
    fun performAction()
}
// Класс для настройки и внедрения зависимостей
object Injector {
    fun provideService(): Service {
        return ServiceImpl()
    }

    fun provideClient(): Client {
        val service = provideService()
        return Client(service)
    }
}