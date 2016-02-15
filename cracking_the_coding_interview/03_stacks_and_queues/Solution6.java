import java.util.LinkedList;
import java.util.Queue;

public class Solution6 {
    
    public static void main(String[] args) {
        new Solution6();
    }

    public Solution6() {
        AnimalShelter shelter = new AnimalShelter();
        shelter.enqueue(new Dog("d1"));
        shelter.enqueue(new Cat("c2"));
        shelter.enqueue(new Dog("d3"));
        System.out.println(shelter.dequeueDog().name);
        System.out.println(shelter.dequeueAny().name);
    }

    private abstract class Animal {
        
        private int timestamp;
        public final String name;

        public Animal(String name) {
            this.name = name;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }

    private class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }

    private class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
    }

    private class AnimalShelter {

        private Queue<Cat> cats = new LinkedList<Cat>();
        private Queue<Dog> dogs = new LinkedList<Dog>();

        private int timestamp = 0;

        public void enqueue(Animal animal) {
            animal.setTimestamp(timestamp++);
            if (animal instanceof Cat) {
                cats.add((Cat) animal);
            } else if (animal instanceof Dog) {
                dogs.add((Dog) animal);
            }
        }

        public Animal dequeueAny() {
            if (cats.isEmpty() && dogs.isEmpty()) {
                return null;    // error
            }
            if (cats.isEmpty()) {
                return dogs.remove();
            }
            if (dogs.isEmpty()) {
                return cats.remove();
            }
            if (cats.peek().getTimestamp() < dogs.peek().getTimestamp()) {
                return cats.remove();
            } else {
                return dogs.remove();
            }
        }

        public Cat dequeueCat() {
            if (cats.isEmpty()) {
                return null;    // error
            }
            return cats.remove();
        }

        public Dog dequeueDog() {
            if (dogs.isEmpty()) {
                return null;    // error
            }
            return dogs.remove();
        }

    }

}