# **Boblet**

Boblet is your friendly personal assistant chatbot! It helps you manage your tasks efficiently, offering support for various types of tasks such as **ToDos, Deadlines, and Events**. Designed as a **command-line application**, Boblet provides an interactive way to organize your daily activities.

## **Table of Contents**

- [Features](#features)
- [Commands](#commands)
- [Setup](#setup)
- [Usage Example](#usage-example)
- [Design Overview](#design-overview)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

---

## **Features**

### ✅ **Task Management**
Boblet allows you to:
- Add tasks of different types:
  - **ToDos**: Simple tasks without a specific deadline.
  - **Deadlines**: Tasks with a specified due date.
  - **Events**: Tasks with a specified time frame.
- List all tasks in your current task list.
- Mark tasks as **completed**.
- Delete tasks from your task list.
- Search tasks by **date or keyword**.

---

### 📌 **Task Types & Commands**

Boblet supports three types of tasks:

#### **1. ToDos**
   - Format: `todo <description>`
   - Example:
     ```plaintext
     You: todo read book
     ```
   - Output:
     ```plaintext
     Got it. I've added this task:
       [T][✗] read book
     Now you have 1 task in the list.
     ```

#### **2. Deadlines**
   - Format: `deadline <description> /by <due date>`
   - Example:
     ```plaintext
     You: deadline return book /by Sunday
     ```
   - Output:
     ```plaintext
     Got it. I've added this task:
       [D][✗] return book (by: Sunday)
     Now you have 2 tasks in the list.
     ```

#### **3. Events**
   - Format: `event <description> /at <time>`
   - Example:
     ```plaintext
     You: event project meeting /at Monday 2-4pm
     ```
   - Output:
     ```plaintext
     Got it. I've added this task:
       [E][✗] project meeting (at: Monday 2-4pm)
     Now you have 3 tasks in the list.
     ```

---

## **Commands**

| **Command**     | **Description**                                                                                        | **Format**                          | **Example**                        |
|----------------|------------------------------------------------------------------------------------------------------|-------------------------------------|-------------------------------------|
| `list`         | Lists all tasks in your current task list.                                                           | `list`                              | `You: list`                        |
| `done`         | Marks a task as done.                                                                                | `done <task number>`                | `You: done 1`                      |
| `delete`       | Deletes a task from the list.                                                                        | `delete <task number>`              | `You: delete 2`                    |
| `todo`         | Adds a ToDo task.                                                                                    | `todo <description>`                | `You: todo buy groceries`          |
| `deadline`     | Adds a Deadline task.                                                                                | `deadline <description> /by <date>` | `You: deadline submit report /by Friday` |
| `event`        | Adds an Event task.                                                                                  | `event <description> /at <time>`    | `You: event team meeting /at 3-5pm` |
| `find`         | Finds tasks containing a specific keyword.                                                           | `find <keyword>`                    | `You: find book`                    |
| `showdate`     | Displays all tasks occurring on a specific date.                                                     | `showdate <date>`                   | `You: showdate Monday`              |
| `bye`          | Exits the application.                                                                               | `bye`                               | `You: bye`                         |

---

## **Usage Example**

```plaintext
____________________________________________________________
Hey there! I'm Boblet, your friendly assistant!
I can track ToDos, Deadlines, and Events. Just tell me what to do!
____________________________________________________________
You: todo read book
____________________________________________________________
Got it. I've added this task:
  [T][✗] read book
____________________________________________________________
You: list
____________________________________________________________
Here are the tasks in your list:
1. [T][✗] read book
____________________________________________________________
You: done 1
____________________________________________________________
Nice! I've marked this task as done:
  [T][✓] read book
____________________________________________________________
You: bye
____________________________________________________________
Aww, you're leaving already! Take care! See you soon!
____________________________________________________________
```

---

## **Design Overview**

### **Architecture**
Boblet's architecture follows an object-oriented design:

1. **Task Class**:
   - Base class for tasks (ToDos, Deadlines, Events).
   - Supports **description, status (done/not done), and task type**.

2. **Task Types**:
   - `Todo` → Simple task.
   - `Deadline` → Task with a deadline.
   - `Event` → Task with a specific time.

3. **Exception Handling**:
   - Custom `BobletException` for user input validation.

---

## **Future Enhancements**

1. **File Persistence**:
   - Save tasks to a file and load them on startup.

2. **Date & Time Parsing**:
   - Use Java’s `LocalDate` for better deadline and event tracking.

3. **Graphical User Interface (GUI)**:
   - Transition from CLI to a **JavaFX-based GUI**.

4. **Task Prioritization**:
   - Introduce task priority levels (High, Medium, Low).

---

## **Contributing**

Contributions are welcome! Fork the repository and submit a pull request.

---

