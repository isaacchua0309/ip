# **Boblet**

Boblet is your friendly personal assistant chatbot! It helps you manage your tasks efficiently, offering support for various types of tasks such as ToDos, Deadlines, and Events. Designed as a command-line application, Boblet provides an interactive way to organize your daily activities.

---

## **Features**

### 1. **Task Management**
Boblet allows you to:
- Add tasks of different types:
  - **ToDos**: Simple tasks without a specific deadline.
  - **Deadlines**: Tasks with a specified due date.
  - **Events**: Tasks with a specified time frame.
- List all tasks in your current task list.
- Mark tasks as completed.
- Delete tasks from your task list.

---

### 2. **Task Types**
Boblet supports three types of tasks:
1. **ToDos**:
   - Format: `todo <description>`
   - Example:
     ```plaintext
     You: todo read book
     ```
   - Output:
     ```plaintext
     Got it. I've added this task:
       [TODO][✗] read book
     Now you have 1 task in the list.
     ```

2. **Deadlines**:
   - Format: `deadline <description> /by <due date>`
   - Example:
     ```plaintext
     You: deadline return book /by Sunday
     ```
   - Output:
     ```plaintext
     Got it. I've added this task:
       [DEADLINE][✗] return book (by: Sunday)
     Now you have 2 tasks in the list.
     ```

3. **Events**:
   - Format: `event <description> /at <time>`
   - Example:
     ```plaintext
     You: event project meeting /at Monday 2-4pm
     ```
   - Output:
     ```plaintext
     Got it. I've added this task:
       [EVENT][✗] project meeting (at: Monday 2-4pm)
     Now you have 3 tasks in the list.
     ```

---

### 3. **Commands**
Boblet supports the following commands:

| **Command**     | **Description**                                                                                            | **Format**                          | **Example**                        |
|------------------|----------------------------------------------------------------------------------------------------------|-------------------------------------|-------------------------------------|
| `list`          | Lists all tasks in your current task list.                                                               | `list`                              | `You: list`                        |
| `done`          | Marks a task as done.                                                                                    | `done <task number>`                | `You: done 1`                      |
| `delete`        | Deletes a task from the list.                                                                            | `delete <task number>`              | `You: delete 2`                    |
| `todo`          | Adds a ToDo task.                                                                                        | `todo <description>`                | `You: todo buy groceries`          |
| `deadline`      | Adds a Deadline task.                                                                                    | `deadline <description> /by <date>` | `You: deadline submit report /by Friday` |
| `event`         | Adds an Event task.                                                                                      | `event <description> /at <time>`    | `You: event team meeting /at 3-5pm` |
| `bye`           | Exits the application.                                                                                   | `bye`                               | `You: bye`                         |

---

### 4. **Error Handling**
Boblet gracefully handles incorrect inputs with descriptive error messages. Examples:

1. **Invalid Commands**:
   ```plaintext
   You: unknowncommand
   ☹ OOPS!!! I'm sorry, but I don't know what that means :-(
   ```

2. **Empty Task Descriptions**:
   ```plaintext
   You: todo
   ☹ OOPS!!! The description of a todo cannot be empty.
   ```

3. **Invalid Task Numbers**:
   ```plaintext
   You: done 99
   ☹ OOPS!!! Invalid task number!
   ```

---

## **Setup**

### **Prerequisites**
- Java Development Kit (JDK) 8 or above installed on your system.

### **Clone the Repository**
To get started, clone the repository:
```bash
git clone <repository-url>
```

### **Compile and Run**
1. Navigate to the project directory:
   ```bash
   cd <project-directory>
   ```
2. Compile the code:
   ```bash
   javac Boblet.java
   ```
3. Run the program:
   ```bash
   java Boblet
   ```

---

## **Example Usage**

### **Interaction**
Below is an example interaction with Boblet:

```plaintext
____________________________________________________________
Hey there! I'm Boblet, your friendly assistant!
I can track ToDos, Deadlines, and Events. Just tell me what to do!
Type 'list' to see your tasks, 'done <number>' to mark a task as done, 'delete <number>' to remove a task, or 'bye' to leave. Let's get started!
____________________________________________________________
You: todo read book
____________________________________________________________
Got it. I've added this task:
  [TODO][✗] read book
Now you have 1 task in the list.
____________________________________________________________
You: deadline return book /by Sunday
____________________________________________________________
Got it. I've added this task:
  [DEADLINE][✗] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
You: event project meeting /at Monday 2-4pm
____________________________________________________________
Got it. I've added this task:
  [EVENT][✗] project meeting (at: Monday 2-4pm)
Now you have 3 tasks in the list.
____________________________________________________________
You: list
____________________________________________________________
Here are the tasks in your list:
1.[TODO][✗] read book
2.[DEADLINE][✗] return book (by: Sunday)
3.[EVENT][✗] project meeting (at: Monday 2-4pm)
____________________________________________________________
You: done 1
____________________________________________________________
Nice! I've marked this task as done:
  [TODO][✓] read book
____________________________________________________________
You: delete 2
____________________________________________________________
Noted. I've removed this task:
  [DEADLINE][✗] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
You: bye
____________________________________________________________
Aww, you're leaving already! Well, take care!
See you soon! Bye from Boblet!
____________________________________________________________
```

---

## **Design Overview**

### **Architecture**
Boblet's architecture follows an object-oriented design with the following components:

1. **`Task` (Abstract Class)**:
   - Base class for tasks.
   - Supports common properties like description, status (done or not), and task type.

2. **Task Types**:
   - `Todo`: A simple task.
   - `Deadline`: A task with a deadline.
   - `Event`: A task with a specific time.

3. **Enums**:
   - `TaskType`: Represents task types (TODO, DEADLINE, EVENT).
   - `CommandType`: Represents user commands (LIST, DONE, DELETE, etc.).

4. **Exception Handling**:
   - Custom `BobletException` for user input validation.

---

## **Future Enhancements**

1. **Persistence**:
   - Save tasks to a file and load them on startup.

2. **Date and Time Parsing**:
   - Use Java's `LocalDate` and `LocalTime` for better handling of deadlines and events.

3. **User Interface**:
   - Transition from the command-line interface to a graphical user interface (GUI).

4. **Search Functionality**:
   - Allow users to search for tasks by keywords or dates.

---

## **Contributing**

Contributions are welcome! Please fork the repository and submit a pull request.

---

## **License**

This project is licensed under the MIT License. See the `LICENSE` file for details.
