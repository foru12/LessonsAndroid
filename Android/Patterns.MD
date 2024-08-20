# Шаблоны проектирования в Android с использованием Kotlin



---

## Введение

Шаблоны проектирования — это, по сути, решения или схемы для проблем, с которыми мы сталкиваемся снова и снова при программировании. В Android существует множество таких шаблонов, которые помогают решать типичные проблемы. Они делятся на три основные категории:

1. **Шаблоны создания**: как вы создаете объекты.
2. **Структурные шаблоны**: как вы организуете классы и объекты.
3. **Поведенческие шаблоны**: как вы координируете взаимодействие объектов.

Теперь давайте рассмотрим наиболее важные из них.

---

## 1. Шаблоны создания

Шаблоны создания имеют дело с механизмами создания объектов. Основное внимание уделяется тому, чтобы создавать объекты в соответствии с ситуацией, избегая проблем, связанных с неправильной или избыточной инициализацией.

### 1.1 Singleton

Шаблон **Singleton** гарантирует, что в программе существует только один экземпляр определенного класса.

```kotlin
object Singleton {
    fun performAction() {
        // Логика выполнения действия
    }
}

// Использование:
Singleton.performAction()
```

**Пример использования**: Используйте этот шаблон для управления глобальным состоянием, например, для единственного подключения к базе данных в приложении.

### 1.2 Builder

Шаблон **Builder** используется для создания сложных объектов пошагово. Это полезно, когда объект имеет много полей или когда порядок их инициализации имеет значение.

```kotlin
class User private constructor(
    val firstName: String,
    val lastName: String,
    val age: Int?
) {
    data class Builder(
        var firstName: String = "",
        var lastName: String = "",
        var age: Int? = null
    ) {
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun age(age: Int?) = apply { this.age = age }
        fun build() = User(firstName, lastName, age)
    }
}

// Использование:
val user = User.Builder()
    .firstName("John")
    .lastName("Doe")
    .age(30)
    .build()
```

**Пример использования**: Полезно при создании объектов с множеством опций и параметров, таких как сложные формы или конфигурации.

### 1.3 Factory

Шаблон **Factory** скрывает логику создания объекта и возвращает объект одного из нескольких возможных типов, в зависимости от входных данных.

```kotlin
interface Shape {
    fun draw()
}

class Circle : Shape {
    override fun draw() {
        println("Drawing a Circle")
    }
}

class Square : Shape {
    override fun draw() {
        println("Drawing a Square")
    }
}

object ShapeFactory {
    fun createShape(type: String): Shape {
        return when (type) {
            "Circle" -> Circle()
            "Square" -> Square()
            else -> throw IllegalArgumentException("Unknown shape type")
        }
    }
}

// Использование:
val shape = ShapeFactory.createShape("Circle")
shape.draw()
```

**Пример использования**: Используйте фабрику, когда нужно создавать объекты из различных подклассов в зависимости от ситуации.

### 1.4 Dependency Injection (DI)

**Внедрение зависимостей (DI)** позволяет передавать зависимости объекта через его конструктор или методы, что упрощает тестирование и конфигурирование.

```kotlin
class NetworkClient(val apiKey: String)

class Repository(val networkClient: NetworkClient)

class MyApplication {
    val networkClient = NetworkClient("API_KEY")
    val repository = Repository(networkClient)
}
```

**Пример использования**: DI особенно полезно для управления зависимостями между классами, такими как подключение к базе данных или сетевой клиент в Android-приложениях.

---

## 2. Структурные шаблоны

Структурные шаблоны проектирования связаны с организацией классов и объектов. Они помогают создавать большие структуры, поддерживая их гибкость и эффективность.


### 2.1 Facade

Шаблон **Facade** предоставляет простой интерфейс для работы с более сложной системой.

```kotlin
class SubsystemA {
    fun operationA() = "SubsystemA: Ready!"
}

class SubsystemB {
    fun operationB() = "SubsystemB: Go!"
}

class Facade(private val subsystemA: SubsystemA, private val subsystemB: SubsystemB) {
    fun operation() = "${subsystemA.operationA()} ${subsystemB.operationB()}"
}

// Использование:
val facade = Facade(SubsystemA(), SubsystemB())
println(facade.operation())
```

**Пример использования**: Упрощает взаимодействие с сложными подсистемами, например, с API или библиотеками.

### 2.2 Adapter

Шаблон **Adapter** позволяет объектам с несовместимыми интерфейсами работать вместе.

```kotlin
interface OldSystem {
    fun oldOperation()
}

class NewSystem {
    fun newOperation() {
        println("New system operation")
    }
}

class Adapter(private val newSystem: NewSystem) : OldSystem {
    override fun oldOperation() {
        newSystem.newOperation()
    }
}

// Использование:
val adapter = Adapter(NewSystem())
adapter.oldOperation()
```

**Пример использования**: Используется при интеграции новых систем с устаревшими API или при переходе с одного интерфейса на другой.

### 2.3 Декоратор

Шаблон **Decorator** используется для добавления новой функциональности объекту во время выполнения без изменения его структуры. Этот паттерн обеспечивает гибкую альтернативу использованию наследования для расширения поведения объектов.

```kotlin
interface Coffee {
    fun cost(): Double
}

class SimpleCoffee : Coffee {
    override fun cost() = 5.0
}

class MilkDecorator(private val coffee: Coffee) : Coffee {
    override fun cost() = coffee.cost() + 1.5
}

class SugarDecorator(private val coffee: Coffee) : Coffee {
    override fun cost() = coffee.cost() + 0.5
}

// Использование:
val coffee = SimpleCoffee()
println("Cost of simple coffee: ${coffee.cost()}")

val milkCoffee = MilkDecorator(coffee)
println("Cost of coffee with milk: ${milkCoffee.cost()}")

val milkSugarCoffee = SugarDecorator(milkCoffee)
println("Cost of coffee with milk and sugar: ${milkSugarCoffee.cost()}")
```

**Пример использования**: Шаблон декоратора полезен для добавления функций к объектам, например, для настройки различных вариантов пользователя в приложении, таких как различные темы оформления.

### 2.4 Composite

Шаблон **Composite** используется для работы с иерархиями объектов. Этот паттерн позволяет объединить объекты в древовидную структуру и работать с ними как с единым объектом.

```kotlin
interface Graphic {
    fun draw()
}

class Dot : Graphic {
    override fun draw() {
        println("Draw Dot")
    }
}

class Circle : Graphic {
    override fun draw() {
        println("Draw Circle")
    }
}

class CompoundGraphic : Graphic {
    private val children = mutableListOf<Graphic>()

    fun add(child: Graphic) {
        children.add(child)
    }

    override fun draw() {
        for (child in children) {
            child.draw()
        }
    }
}

// Использование:
val compoundGraphic = CompoundGraphic()
compoundGraphic.add(Dot())
compoundGraphic.add(Circle())
compoundGraphic.draw()
```

**Пример использования**: Этот шаблон часто используется в реализации UI-компонентов, где каждый элемент интерфейса (например, кнопка или текстовое поле) может содержать другие элементы.

### 2.5 Прокси

Шаблон **Proxy** предоставляет суррогатный или замещающий объект для управления доступом к другому объекту. Прокси может выполнять дополнительные действия (например, кэширование или контроль доступа) до или после передачи вызовов реальному объекту.

```kotlin
interface Image {
    fun display()
}

class RealImage(private val fileName: String) : Image {
    init {
        loadFromDisk()
    }

    override fun display() {
        println("Displaying $fileName")
    }

    private fun loadFromDisk() {
        println("Loading $fileName from disk")
    }
}

class ProxyImage(private val fileName: String) : Image {
    private var realImage: RealImage? = null

    override fun display() {
        if (realImage == null) {
            realImage = RealImage(fileName)
        }
        realImage?.display()
    }
}

// Использование:
val image = ProxyImage("test_image.jpg")
image.display()  // Загружает изображение и отображает его
image.display()  // Только отображает, без повторной загрузки
```

**Пример использования**: Прокси часто используется для управления доступом к ресурсоемким объектам, таким как изображения или соединения с базой данных, или для реализации ленивой загрузки данных.

---

---

## 3. Поведенческие шаблоны

Поведенческие шаблоны проектирования связаны с управлением взаимодействиями между объектами.

### 3.1 Observer

Шаблон **Observer** позволяет объектам подписываться на события и получать уведомления о изменениях состояния другого объекта.

```kotlin
class Subject {
    private val observers = mutableListOf<Observer>()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun notifyObservers() {
        for (observer in observers) {
            observer.update()
        }
    }
}

interface Observer {
    fun update()
}

class ConcreteObserver : Observer {
    override fun update() {
        println("Observer updated")
    }
}

// Использование:
val subject = Subject()
val observer = ConcreteObserver()
subject.addObserver(observer)
subject.notifyObservers()
```

**Пример использования**: Полезно для реализации паттерна "подписка" в UI-компонентах или при работе с событиями.

### 3.2 Strategy

Шаблон **Strategy** используется для создания взаимозаменяемого семейства алгоритмов, из которых во время выполнения выбирается требуемый процесс.

```kotlin
interface Strategy {
    fun execute(a: Int, b: Int): Int
}

class AdditionStrategy : Strategy {
    override fun execute(a: Int, b: Int): Int = a + b
}

class SubtractionStrategy : Strategy {
    override fun execute(a: Int, b: Int): Int = a - b
}

class Context(private var strategy: Strategy) {
    fun setStrategy(strategy: Strategy) {
        this.strategy = strategy
    }

    fun executeStrategy(a: Int, b: Int): Int {
        return strategy.execute(a, b)
    }
}

// Использование:
val context = Context(AdditionStrategy())
println("Result: ${context.executeStrategy(10, 5)}") // 15

context.setStrategy(SubtractionStrategy())
println("Result: ${context.executeStrategy(10, 5)}") // 5
```

**Пример использования**: Стратегия полезна, когда вам нужно выбрать алгоритм на основе определенного контекста или условий.

### 3.3 Command

Шаблон **Command** используется для инкапсуляции запроса в объект, позволяя параметризовать клиентов с различными запросами, ставить запросы в очередь или логировать их.

```kotlin
interface Command {
    fun execute()
}

class Light {
    fun on() {
        println("Light is ON")
    }

    fun off() {
        println("Light is OFF")
    }
}

class LightOnCommand(private val light: Light) : Command {
    override fun execute() {
        light.on()
    }
}

class LightOffCommand(private val light: Light) : Command {
    override fun execute() {
        light.off()
    }
}

class RemoteControl {
    private val commandMap = mutableMapOf<String, Command>()

    fun setCommand(button: String, command: Command) {
        commandMap[button] = command
    }

    fun pressButton(button: String) {
        commandMap[button]?.execute()
    }
}

// Использование:
val light = Light()
val lightOnCommand = LightOnCommand(light)
val lightOffCommand = LightOffCommand(light)

val remote = RemoteControl()
remote.setCommand("ON", lightOnCommand)
remote.setCommand("OFF", lightOffCommand)

remote.pressButton("ON")
remote.pressButton("OFF")
```

**Пример использования**: Команда часто используется для реализации действий в интерфейсе пользователя, например, для реализации кнопок отмены или выполнения.

### 3.4 State

Шаблон **State** позволяет объекту изменять свое поведение при изменении его внутреннего состояния. Это похоже на изменение класса объекта во время выполнения.

```kotlin
interface State {
    fun doAction(context: Context)
}

class StartState : State {
    override fun doAction(context: Context) {
        println("Player is in start state")
        context.setState(this)
    }

    override fun toString(): String = "Start State"
}

class StopState : State {
    override fun doAction(context: Context) {
        println("Player is in stop state")
        context.setState(this)
    }

    override fun toString(): String = "Stop State"
}

class Context {
    private var state: State? = null

    fun setState(state: State) {
        this.state = state
    }

    fun getState(): State? = state
}

// Использование:
val context = Context()

val startState = StartState()
startState.doAction(context)

println(context.getState().toString())

val stopState = StopState()
stopState.doAction(context)

println(context.getState().toString())
```

**Пример использования**: State полезен для управления объектами, которые изменяют свое поведение в зависимости от внутреннего состояния, например, для управления состоянием подключения.

### 3.5 Chain of Responsibility

Шаблон **Chain of Responsibility** передает запрос по цепочке обработчиков. Каждый обработчик решает, может ли он обработать запрос или передать его следующему обработчику в цепочке.

```kotlin
abstract class Logger(protected val level: Int) {
    protected var nextLogger: Logger? = null

    fun setNextLogger(nextLogger: Logger) {
        this.nextLogger = nextLogger
    }

    fun logMessage(level: Int, message: String) {
        if (this.level <= level) {
            write(message)
        }
        nextLogger?.logMessage(level, message)
    }

    abstract fun write(message: String)
}

class ConsoleLogger(level: Int) : Logger(level) {
    override fun write(message: String) {
        println("Standard Console::Logger: $message")
    }
}

class ErrorLogger(level: Int) : Logger(level) {
    override fun write(message: String) {
        println("Error Console::Logger: $message")
    }
}

class FileLogger(level: Int) : Logger(level) {
    override fun write(message: String) {
        println("File::Logger: $message")
    }
}

// Использование:
val errorLogger = ErrorLogger(1)
val fileLogger = FileLogger(2)
val consoleLogger = ConsoleLogger(3)

errorLogger.setNextLogger(fileLogger)
fileLogger.setNextLogger(consoleLogger)

errorLogger.logMessage(1, "This is an error message.")
errorLogger.logMessage(2, "This is a file log message.")
errorLogger.logMessage(3, "This is a console log message.")
```

**Пример использования**: Этот шаблон полезен для обработки различных запросов, где каждый запрос может обрабатываться разными обработчиками, например, логирование или авторизация.
