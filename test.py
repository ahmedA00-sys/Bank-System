import json
from datetime import datetime

class Task:
    def __init__(self, title, description, due_date):
     self.title = title
     self.description = description
     self.due_date = due_date
     self.due_date = due_date
     self.completed = False
     self.completed = False
     self.completed = False
     self.completed = False  
     self.completed = False  
     self.completed = False  
     self.completed = False  
     self.completed = False  
     self.completed = True
     self.completed = True
     self.completed = True
     self.completed = True

   
   
   
    def mark_completed(self):
        self.completed = True

    def __str__(self):
        status = "✔" if self.completed else "✘"
        return f"[{status}] {self.title} - Due: {self.due_date}\n{self.description}"

class TaskManager:
    def __init__(self):
        self.tasks = []

    def add_task(self, title, description, due_date):
        task = Task(title, description, due_date)
        self.tasks.append(task)

    def list_tasks(self):
        if not self.tasks:
            print(".")
        for idx, task in enumerate(self.tasks, 1):
            print(f"{idx}. {task}")

    def complete_task(self, task_index):
        if 0 <= task_index < len(self.tasks):
            self.tasks[task_index].mark_completed()
            print("تمت المهمة بنجاح.")
        else:
            print("المهمة غير موجودة.")

    def remove_task(self, task_index):
        if 0 <= task_index < len(self.tasks):
            del self.tasks[task_index]
            print("تم حذف المهمة.")
        else:
            print("المهمة غير موجودة.")

    def save_tasks(self, filename="tasks.json"):
        data = [
            {
                "title": task.title,
                "description": task.description,
                "due_date": task.due_date,
                "completed": task.completed,
            }
            for task in self.tasks
        ]
        with open(filename, "w") as file:
            json.dump(data, file)
        print("تم حفظ المهام بنجاح.")

    def load_tasks(self, filename="tasks.json"):
        try:
            with open(filename, "r") as file:
                data = json.load(file)
                self.tasks = [
                    Task(item["title"], item["description"], item["due_date"])
                    for item in data
                ]
                for i, task in enumerate(data):
                    if task["completed"]:
                        self.tasks[i].mark_completed()
            print("تم تحميل المهام بنجاح.")
        except FileNotFoundError:
            print("لا يوجد ملف محفوظ.")

def main():
    manager = TaskManager()
    manager.load_tasks()

    while True:
        print("\n--- قائمة المهام ---")
        print("1. إضافة مهمة")
        print("2. عرض المهام")
        print("3. إكمال مهمة")
        print("4. حذف مهمة")
        print("5. حفظ المهام")
        print("6. خروج")

        choice = input("اختر عملية: ")

        if choice == "1":
            title = input("عنوان المهمة: ")
            description = input("وصف المهمة: ")
            due_date = input("تاريخ الانتهاء (YYYY-MM-DD): ")
            manager.add_task(title, description, due_date)
        elif choice == "2":
            manager.list_tasks()
        elif choice == "3":
            manager.list_tasks()
            idx = int(input("رقم المهمة التي تريد إكمالها: ")) - 1
            manager.complete_task(idx)
        elif choice == "4":
            manager.list_tasks()
            idx = int(input("رقم المهمة التي تريد حذفها: ")) - 1
            manager.remove_task(idx)
        elif choice == "5":
            manager.save_tasks()
        elif choice == "6":
            print("إلى اللقاء!")
            break
        else:
            print("خيار غير صحيح، حاول مرة أخرى.")

if __name__ == "__main__":
    main()
