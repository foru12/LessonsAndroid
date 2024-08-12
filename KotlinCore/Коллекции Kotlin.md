
# Коллекции в Kotlin

Коллекции в Kotlin — это структуры данных, которые позволяют хранить группы объектов. Коллекции в Kotlin бывают двух основных типов: **изменяемые (mutable)** и **неизменяемые (immutable)**. Это различие важно, потому что изменяемые коллекции позволяют изменять их содержимое (добавлять, удалять элементы), а неизменяемые — нет.

## Основные типы коллекций

### Список (List)

- **List** представляет собой упорядоченную коллекцию элементов, к которым можно обращаться по индексу.
- **MutableList** — изменяемая версия списка.

### Множество (Set)

- **Set** — коллекция уникальных элементов. Порядок элементов не гарантируется.
- **MutableSet** — изменяемая версия множества.

### Словарь (Map)

- **Map** — коллекция пар ключ-значение, где каждый ключ уникален.
- **MutableMap** — изменяемая версия словаря.

## Примеры

### Неизменяемый список (List)

```kotlin
val fruits: List<String> = listOf("Apple", "Banana", "Cherry")
println(fruits[0])  // Вывод: Apple
```

В данном примере создается неизменяемый список `fruits`, который содержит три элемента.

### Изменяемый список (MutableList)

```kotlin
val fruits: MutableList<String> = mutableListOf("Apple", "Banana", "Cherry")
fruits.add("Orange")
println(fruits)  // Вывод: [Apple, Banana, Cherry, Orange]
```

Здесь создается изменяемый список `fruits`, и мы можем добавлять новые элементы в этот список с помощью метода `add`.

### Неизменяемое множество (Set)

```kotlin
val numbers: Set<Int> = setOf(1, 2, 3, 2)
println(numbers)  // Вывод: [1, 2, 3]
```

Множество `numbers` содержит только уникальные элементы, поэтому второй элемент `2` не дублируется.

### Изменяемое множество (MutableSet)

```kotlin
val numbers: MutableSet<Int> = mutableSetOf(1, 2, 3)
numbers.add(4)
println(numbers)  // Вывод: [1, 2, 3, 4]
```

Здесь мы можем добавлять новые элементы в изменяемое множество `numbers`.

### Неизменяемый словарь (Map)

```kotlin
val fruitsMap: Map<String, Int> = mapOf("Apple" to 1, "Banana" to 2, "Cherry" to 3)
println(fruitsMap["Apple"])  // Вывод: 1
```

Словарь `fruitsMap` ассоциирует строки (названия фруктов) с целыми числами.

### Изменяемый словарь (MutableMap)

```kotlin
val fruitsMap: MutableMap<String, Int> = mutableMapOf("Apple" to 1, "Banana" to 2)
fruitsMap["Cherry"] = 3
println(fruitsMap)  // Вывод: {Apple=1, Banana=2, Cherry=3}
```

Здесь мы добавляем новую пару ключ-значение в изменяемый словарь `fruitsMap`.

## Основные операции с коллекциями

### Итерирование (Перебор)

Для перебора элементов можно использовать циклы `for`.

```kotlin
val fruits = listOf("Apple", "Banana", "Cherry")
for (fruit in fruits) {
    println(fruit)
}
```

### Фильтрация

Можно фильтровать коллекции, используя метод `filter`.

```kotlin
val fruits = listOf("Apple", "Banana", "Cherry", "Avocado")
val filteredFruits = fruits.filter { it.startsWith("A") }
println(filteredFruits)  // Вывод: [Apple, Avocado]
```

### Преобразование

Можно преобразовывать элементы коллекции с помощью метода `map`.

```kotlin
val numbers = listOf(1, 2, 3)
val doubled = numbers.map { it * 2 }
println(doubled)  // Вывод: [2, 4, 6]
```
