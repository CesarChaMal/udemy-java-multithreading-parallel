# CLAUDE.md - AI Assistant Guide

> **Repository**: udemy-java-multithreading-parallel
> **Type**: Educational Java Multithreading Course
> **Course**: [Multithreading and Parallel Computing in Java (Udemy)](https://www.udemy.com/course/multithreading-and-parallel-computing-in-java)
> **Last Updated**: 2025-11-13

## Table of Contents
1. [Repository Overview](#repository-overview)
2. [Project Structure](#project-structure)
3. [Development Environment](#development-environment)
4. [Code Conventions](#code-conventions)
5. [Common Patterns](#common-patterns)
6. [Project Categories](#project-categories)
7. [Working with the Codebase](#working-with-the-codebase)
8. [Testing and Running](#testing-and-running)
9. [Key Concepts](#key-concepts)
10. [AI Assistant Guidelines](#ai-assistant-guidelines)

---

## Repository Overview

This is an educational repository containing **24+ Java projects** demonstrating multithreading and parallel computing concepts. The codebase is designed for the Udemy course and uses only **JDK standard library** (no external dependencies).

### Key Statistics
- **Total Java Files**: 66
- **Project Categories**: 11
- **Build System**: None (plain Java, IDE-based)
- **Java Version**: Compatible with Java 8+
- **Dependencies**: JDK only (java.util.concurrent.*)

### Core Technologies
- Java Threading API (`Thread`, `Runnable`)
- Executor Framework (`ExecutorService`, `ThreadPoolExecutor`)
- Fork/Join Framework (`ForkJoinPool`, `RecursiveTask`, `RecursiveAction`)
- Concurrent Collections (`BlockingQueue`, `ConcurrentHashMap`)
- Synchronization (`ReentrantLock`, `Condition`, `CountDownLatch`, `CyclicBarrier`)
- Java Swing (for GUI projects)

---

## Project Structure

```
udemy-java-multithreading-parallel/
│
├── BasicMultithreading/              # 14 lecture-based projects
│   ├── Lecture01_Concurrency/        # Sequential execution (baseline)
│   ├── Lecture02_Concurrency/        # Implementing Runnable
│   ├── Lecture03_Concurrency/        # Extending Thread
│   ├── Lecture04-12_Concurrency/     # Advanced concepts
│   ├── ExecutorServices/             # Thread pools
│   └── FutureCallable/               # Async tasks with return values
│
├── ForkJoinFramework/                # Divide-and-conquer parallel processing
│   ├── ForkJoinSimple1/              # Basic RecursiveAction
│   ├── ForkJoinSimple2/              # RecursiveAction variant
│   ├── ForkJoinMax/                  # Finding maximum (RecursiveTask)
│   └── ForkJoinMerge/                # Parallel merge sort
│
├── ConcurrentLibrary/                # java.util.concurrent utilities
│   └── src/com/balazsholczer/threads/concurrentlibrary/
│       ├── blockingqueues/           # Producer-consumer patterns
│       ├── priorityblockingqueues/   # Priority-based queues
│       ├── delayqueues/              # Delayed element queues
│       ├── cyclicbarriers/           # Synchronization points
│       ├── countdownlatches/         # Countdown coordination
│       ├── concurrentmaps/           # Thread-safe maps
│       └── exchangers/               # Thread data exchange
│
├── ParallelSum/                      # Sequential vs parallel comparison
├── Parallel/                         # Parallel merge sort
├── StudentLibraryProject/            # Multi-threaded library system
├── PhilosopherProblem/               # Classic deadlock demonstration
├── LifeGameSimulation/               # Conway's Game of Life (GUI)
├── MineProject/                      # Minesweeper-like game (GUI)
│
├── Slides/                           # PowerPoint presentations
├── README.md                         # Project description
└── .gitignore                        # Ignore patterns
```

---

## Development Environment

### Build System
**No build system** (Maven/Gradle not used). Projects are designed to be:
- Opened in IDE (Eclipse/IntelliJ IDEA)
- Compiled manually via `javac`
- Run independently

### Compilation (Manual)
```bash
# Navigate to project directory
cd BasicMultithreading/Lecture02_Concurrency

# Compile
javac -d bin src/com/balazsholczer/threads/App.java

# Run
java -cp bin com.balazsholczer.threads.App
```

### IDE Setup
Each project can be imported as a standalone Java project:
1. Open IDE (Eclipse/IntelliJ)
2. Import as Java Project
3. Set source folder: `src/`
4. Run `App.java` main class

### Ignored Files (.gitignore)
- Compiled output: `*.class`, `/build`, `/target`, `/bin`
- IDE files: `*.iml`, `.idea/`, `.classpath`, `.project`
- System files: `.DS_Store`, `Thumbs.db`

---

## Code Conventions

### Package Naming
All projects follow reverse-domain naming convention:
```
com.balazsholczer.threads.<project-name>.<sub-category>
```

**Examples**:
- `com.balazsholczer.threads` (BasicMultithreading lectures)
- `com.balazsholczer.threads.parallelsum`
- `com.balazsholczer.threads.philosopherproblem`
- `com.balazsholczer.threads.concurrentlibrary.blockingqueues`
- `com.balazsholczer.forkjoinmax`
- `com.balazsholczer.forkjoinmerge`

### Sub-package Patterns
- `.app` - Application entry point and main frames
- `.gui` / `.view` - GUI components (Swing-based)
- `.workers` - Worker threads/tasks
- `.constants` - Constants and enums
- `.callbacks` - Event listeners

### Class Naming Conventions
- **Entry point**: `App.java` (contains `main()`)
- **Workers**: `Worker`, `ParallelWorker`, `FirstWorker`, `SecondWorker`
- **Tasks**: `MaximumFindTask`, `MergeSortTask`, `SimpleRecursiveAction`
- **GUI**: `MainFrame`, `Board`, `Cell`, `Toolbar`, `Controller`
- **Constants**: `Constants.java` (static final fields)
- **Enums**: `State.java`

### Code Style
- **Braces**: Opening brace on same line
- **Indentation**: Tabs (not spaces)
- **Comments**: Minimal; educational examples are self-documenting
- **Error handling**: `try-catch` for `InterruptedException`
- **Resource cleanup**: `finally` blocks for `ExecutorService.shutdown()`

---

## Common Patterns

### Pattern 1: Main Method Orchestrator
**Used in**: All projects

Every project has an `App.java` with `main()` that:
1. Initializes resources
2. Creates threads/executors
3. Waits for completion
4. Displays results

```java
public class App {
    public static void main(String[] args) {
        // 1. Setup
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // 2. Submit tasks
        for (int i = 0; i < tasks; i++) {
            executor.execute(new Worker());
        }

        // 3. Shutdown
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(1000);
        }

        // 4. Results
        System.out.println("Done");
    }
}
```

### Pattern 2: Runnable Implementation
**Used in**: BasicMultithreading, ConcurrentLibrary, StudentLibraryProject

```java
class Worker implements Runnable {
    @Override
    public void run() {
        // Thread work here
    }
}

// Usage
Thread t = new Thread(new Worker());
t.start();
```

### Pattern 3: Thread Extension
**Used in**: ParallelSum, Lecture03_Concurrency

```java
public class ParallelWorker extends Thread {
    @Override
    public void run() {
        // Computation logic
    }
}

// Usage
ParallelWorker worker = new ParallelWorker();
worker.start();
worker.join(); // Wait for completion
```

### Pattern 4: ReentrantLock Synchronization
**Used in**: PhilosopherProblem, StudentLibraryProject

```java
private Lock lock = new ReentrantLock();

public void criticalSection() {
    try {
        lock.lock();
        // Protected code
    } finally {
        lock.unlock(); // ALWAYS in finally
    }
}
```

### Pattern 5: Blocking Queue (Producer-Consumer)
**Used in**: ConcurrentLibrary/blockingqueues

```java
BlockingQueue<String> queue = new ArrayBlockingQueue<>(capacity);

// Producer
class Producer implements Runnable {
    public void run() {
        queue.put(item); // Blocks if full
    }
}

// Consumer
class Consumer implements Runnable {
    public void run() {
        String item = queue.take(); // Blocks if empty
    }
}
```

### Pattern 6: ForkJoinPool with RecursiveTask
**Used in**: ForkJoinMax, ForkJoinMerge

```java
public class Task extends RecursiveTask<Long> {
    @Override
    protected Long compute() {
        if (workload < THRESHOLD) {
            return sequentialCompute(); // Base case
        } else {
            Task left = new Task(leftHalf);
            Task right = new Task(rightHalf);
            invokeAll(left, right);     // Parallel execution
            return left.join() + right.join(); // Combine results
        }
    }
}

// Usage
ForkJoinPool pool = new ForkJoinPool(numThreads);
Long result = pool.invoke(new Task(data));
```

### Pattern 7: ExecutorService with Callable/Future
**Used in**: FutureCallable

```java
Callable<Integer> task = () -> {
    // Computation
    return result;
};

ExecutorService executor = Executors.newFixedThreadPool(1);
Future<Integer> future = executor.submit(task);

// Wait for result
Integer result = future.get(); // Blocking call
```

### Pattern 8: Constants Class
**Used in**: PhilosopherProblem, StudentLibraryProject, LifeGameSimulation

```java
public class Constants {
    private Constants() {} // Prevent instantiation

    public static final int NUMBER_OF_PHILOSOPHERS = 5;
    public static final int SIMULATION_RUNNING_TIME = 10000;
}
```

### Pattern 9: Sequential vs Parallel Comparison
**Used in**: ParallelSum, Parallel

```java
// Sequential baseline
long start = System.currentTimeMillis();
long result = sequentialSum(nums);
System.out.println("Sequential: " + (System.currentTimeMillis() - start) + "ms");

// Parallel version
start = System.currentTimeMillis();
long result2 = parallelSum(nums);
System.out.println("Parallel: " + (System.currentTimeMillis() - start) + "ms");
```

### Pattern 10: Swing GUI with Threading
**Used in**: LifeGameSimulation, MineProject

```java
// Main frame setup
SwingUtilities.invokeLater(() -> {
    JFrame frame = new MainFrame();
    frame.setVisible(true);
});

// Background worker
public class Controller implements Runnable {
    @Override
    public void run() {
        while (running) {
            // Long-running simulation
            SwingUtilities.invokeLater(() -> {
                board.repaint(); // Update UI on EDT
            });
        }
    }
}
```

---

## Project Categories

### 1. BasicMultithreading (14 projects)
**Purpose**: Progressive introduction to threading concepts

| Project | Concepts |
|---------|----------|
| Lecture01 | Sequential execution (baseline) |
| Lecture02 | Implementing `Runnable` |
| Lecture03 | Extending `Thread` |
| Lecture04-12 | Synchronization, locks, wait/notify, deadlock |
| ExecutorServices | Thread pools, `ExecutorService` |
| FutureCallable | `Callable`, `Future`, async results |

### 2. ForkJoinFramework (4 projects)
**Purpose**: Divide-and-conquer parallelism

| Project | Description |
|---------|-------------|
| ForkJoinSimple1 | Basic `RecursiveAction` (no return value) |
| ForkJoinSimple2 | Another `RecursiveAction` example |
| ForkJoinMax | Finding maximum using `RecursiveTask<Long>` |
| ForkJoinMerge | Parallel merge sort algorithm |

**Key files**: `App.java`, `*Task.java`, `SequentialMaxFind.java`

### 3. ConcurrentLibrary (7 modules)
**Purpose**: Demonstrate `java.util.concurrent` utilities

| Module | Classes Used |
|--------|--------------|
| blockingqueues | `ArrayBlockingQueue` |
| priorityblockingqueues | `PriorityBlockingQueue` |
| delayqueues | `DelayQueue`, `Delayed` |
| cyclicbarriers | `CyclicBarrier` |
| countdownlatches | `CountDownLatch` |
| concurrentmaps | `ConcurrentHashMap` |
| exchangers | `Exchanger` |

### 4. Practical Applications (6 projects)

#### ParallelSum
Compares sequential vs parallel array summation. Shows performance benefits of parallelism.

**Files**: `App.java`, `SequentialSum.java`, `ParallelSum.java`, `ParallelWorker.java`

#### Parallel
Parallel merge sort implementation using manual thread management.

**Files**: `App.java`, `MergeSort.java`, `ParallelMergeSort.java`

#### StudentLibraryProject
Multi-threaded library system with book borrowing/returning using `ReentrantLock`.

**Files**: `App.java`, `Student.java`, `Book.java`, `Constants.java`

#### PhilosopherProblem
Classic dining philosophers problem demonstrating deadlock and its prevention.

**Files**: `App.java`, `Philosopher.java`, `ChopStick.java`, `Constants.java`, `State.java`

#### LifeGameSimulation
Conway's Game of Life with Swing GUI and threaded simulation.

**Package structure**: `.app`, `.gui`, `.constants`, `.callbacks`

#### MineProject
Minesweeper-like game with parallel mine laying and sweeping.

**Package structure**: `.app`, `.view`, `.workers`, `.constants`

---

## Working with the Codebase

### Navigation Tips
1. **Find entry points**: Look for `App.java` in each project
2. **Understand flow**: `main()` → create workers → execute → wait → display
3. **Check constants**: Look for `Constants.java` for configuration values
4. **GUI projects**: Check `.gui` or `.view` packages for UI components

### Reading Projects
**Recommended order for learning**:
1. BasicMultithreading/Lecture01-03 (fundamentals)
2. BasicMultithreading/ExecutorServices
3. ParallelSum (performance comparison)
4. ConcurrentLibrary/blockingqueues (producer-consumer)
5. PhilosopherProblem (deadlock)
6. ForkJoinFramework/ForkJoinSimple1
7. ForkJoinFramework/ForkJoinMax
8. Advanced GUI projects (LifeGameSimulation, MineProject)

### File Path References
When referencing code, use the format:
```
BasicMultithreading/Lecture02_Concurrency/src/com/balazsholczer/threads/App.java:26
```

### Common Issues
- **No build files**: This is expected; use IDE or manual compilation
- **Missing dependencies**: Should not happen; JDK only
- **Threading issues**: Demonstrates concurrency problems intentionally (e.g., deadlock)

---

## Testing and Running

### Running Individual Projects
```bash
# Example: ParallelSum
cd ParallelSum/src
javac com/balazsholczer/threads/parallelsum/*.java
java com.balazsholczer.threads.parallelsum.App
```

### Expected Output Patterns
- **Timing comparisons**: Sequential vs Parallel execution times
- **Thread interleaving**: Mixed output from multiple threads
- **GUI windows**: Interactive simulations (LifeGame, MineProject)
- **Deadlock**: PhilosopherProblem may demonstrate stuck threads

### Verification
- Check console output for completion messages
- Verify timing comparisons show parallel speedup
- GUI projects should display interactive windows
- No compilation errors (Java 8+)

---

## Key Concepts

### Concurrency Primitives
- **Thread**: `Thread`, `Runnable`, `start()`, `join()`
- **Synchronization**: `synchronized`, `volatile`, `ReentrantLock`
- **Coordination**: `wait()`, `notify()`, `notifyAll()`, `Condition`

### Executor Framework
- **Thread pools**: `Executors.newFixedThreadPool()`, `ExecutorService`
- **Lifecycle**: `execute()`, `submit()`, `shutdown()`, `isTerminated()`
- **Async results**: `Callable<T>`, `Future<T>`, `get()`

### Fork/Join Framework
- **Tasks**: `RecursiveTask<T>` (returns value), `RecursiveAction` (no return)
- **Methods**: `compute()`, `fork()`, `join()`, `invokeAll()`
- **Pool**: `ForkJoinPool`, work-stealing algorithm

### Concurrent Collections
- **Blocking**: `BlockingQueue`, `put()`, `take()` (blocking operations)
- **Priority**: `PriorityBlockingQueue`, `DelayQueue`
- **Maps**: `ConcurrentHashMap` (lock-free reads)

### Synchronization Tools
- **Latches**: `CountDownLatch` (one-time barrier)
- **Barriers**: `CyclicBarrier` (reusable barrier)
- **Exchangers**: `Exchanger` (pair-wise data exchange)
- **Semaphores**: Control access to resources

---

## AI Assistant Guidelines

### When Modifying Code

#### DO:
- Preserve package structure (`com.balazsholczer.threads.*`)
- Keep `App.java` as main entry point
- Use tabs for indentation (match existing style)
- Add proper `try-finally` for lock releases
- Include timing measurements for parallel comparisons
- Add `InterruptedException` handling
- Use `ExecutorService.shutdown()` and wait for termination
- Document intentional concurrency issues (for education)

#### DON'T:
- Add external dependencies (keep JDK-only)
- Create build files (Maven/Gradle) unless explicitly requested
- Change package naming convention
- Remove educational comments
- Fix intentional bugs (e.g., deadlock in PhilosopherProblem)
- Add complex frameworks (Spring, etc.)

### Creating New Examples
Follow this template:
```java
package com.balazsholczer.threads.newexample;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            // 1. Setup
            executor = Executors.newFixedThreadPool(numThreads);

            // 2. Submit tasks
            // ...

            // 3. Wait
            Thread.sleep(SIMULATION_TIME);

        } finally {
            // 4. Cleanup
            if (executor != null) {
                executor.shutdown();
                while (!executor.isTerminated()) {
                    Thread.sleep(1000);
                }
            }
        }
    }
}
```

### Explaining Code
- Reference specific projects and files
- Compare sequential vs parallel versions
- Highlight thread safety issues
- Explain synchronization mechanisms
- Show performance trade-offs
- Link to lecture numbers when applicable

### Common Questions

**Q: Why no build system?**
A: Educational simplicity; focuses on Java concurrency APIs, not build tools.

**Q: Which Java version?**
A: Java 8+ (uses `java.util.concurrent`, lambdas in some places)

**Q: How to run all projects?**
A: Each project is independent; run individually via IDE or `javac`/`java`.

**Q: Why intentional bugs?**
A: Demonstrates concurrency problems (deadlock, race conditions) for learning.

**Q: Can I add Maven/Gradle?**
A: Yes, but preserve existing structure and document changes.

### Best Practices for AI Assistants

1. **Always check `Constants.java`** for configuration values
2. **Read `App.java` first** to understand project flow
3. **Preserve educational intent** - don't over-engineer
4. **Test concurrency behavior** - run multiple times to verify
5. **Reference specific files** with full paths
6. **Explain trade-offs** between sequential and parallel
7. **Maintain consistency** across similar projects
8. **Document timing results** when modifying algorithms
9. **Respect threading patterns** already established
10. **Keep it simple** - this is teaching material

### Code Review Checklist
- [ ] Package name follows convention
- [ ] `App.java` has `main()` method
- [ ] Locks released in `finally` blocks
- [ ] `ExecutorService` properly shut down
- [ ] `InterruptedException` caught and handled
- [ ] Timing measurements for parallel code
- [ ] No external dependencies added
- [ ] Tabs used for indentation
- [ ] Educational comments preserved
- [ ] Thread-safe access to shared data

---

## Quick Reference

### Project Lookup Table

| Concept | Project(s) | Key Files |
|---------|-----------|-----------|
| Basic threading | Lecture02, Lecture03 | `App.java` |
| Synchronization | Lecture04-09 | `App.java` |
| Deadlock | PhilosopherProblem | `Philosopher.java`, `ChopStick.java` |
| Thread pools | ExecutorServices | `App.java` |
| Callable/Future | FutureCallable | `App.java` |
| BlockingQueue | ConcurrentLibrary/blockingqueues | `BlockingQueues.java` |
| CountDownLatch | ConcurrentLibrary/countdownlatches | `CountDownLatches.java` |
| CyclicBarrier | ConcurrentLibrary/cyclicbarriers | `CyclicBarriers.java` |
| ForkJoin basics | ForkJoinSimple1, ForkJoinSimple2 | `SimpleRecursiveAction.java` |
| RecursiveTask | ForkJoinMax | `MaximumFindTask.java` |
| Parallel merge sort | ForkJoinMerge, Parallel | `MergeSortTask.java`, `ParallelMergeSort.java` |
| Performance comparison | ParallelSum | `SequentialSum.java`, `ParallelSum.java` |
| GUI threading | LifeGameSimulation, MineProject | `Controller.java`, `MainFrame.java` |
| Resource locking | StudentLibraryProject | `Book.java`, `Student.java` |

### Common Commands
```bash
# Find all App.java files
find . -name "App.java"

# Count Java files
find . -name "*.java" | wc -l

# Search for specific pattern
grep -r "ExecutorService" --include="*.java"

# Compile and run example
cd ParallelSum/src
javac com/balazsholczer/threads/parallelsum/*.java
java com.balazsholczer.threads.parallelsum.App
```

---

## Additional Resources

- **Course**: https://www.udemy.com/course/multithreading-and-parallel-computing-in-java
- **Slides**: `Slides/threads.pptx`
- **README**: `README.md`

---

**Document Version**: 1.0
**Generated**: 2025-11-13
**For**: AI assistants working with this codebase
