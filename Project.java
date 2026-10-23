import java.util.LinkedList;
import java.util.Scanner;
// This PrintJob class to identify the attributes of print jobs
class PrintJob {
    // Attributes of a Print Job
    private String File_Name; // instance variable (String): File Name
    private int Pages_Number; // instance variable (Integer): Page Number
    // Constructor
    public PrintJob (String File_Name , int Pages_Number) {
        this.File_Name = File_Name;
        this.Pages_Number = Pages_Number;
    }
    // getter method for the file name
    public String getFileName () {
        return File_Name;
    }
    // getter method for the Pages Number
    public int getPagesNumber () {
        return Pages_Number;
    }
    @Override
    public String toString () {
        return "Print Job: {" + "File Name='" + File_Name + '\'' + ", Pages Number=" + Pages_Number + '}';
    }
}
// This class to identify the algorithms that will be implemented on the queue of print jobs
class PrintQueue {
    private LinkedList <PrintJob> queue;
    // Constructor
    public PrintQueue () {
        this.queue = new LinkedList<>();
    }
    // a. Insertion algorithm (Enqueue) implemented by the method 'enqueue'
    public void enqueue (PrintJob printJob ) {
        queue.addLast(printJob); // Inserting new print job in the queue at the 'rear'
    }
    // b. Deletion algorithm (Dequeue) implemented by the method 'dequeue'
    public PrintJob dequeue () {
        if (isEmpty()) {
            System.out.println("Error: Queue Is Empty (UnderFlow).");
            return null;
        }
        return queue.removeFirst(); // to process the 'front' print job and then delete it from the queue
    }
    // c. Traversal algorithm to pass through each print job and process it then delete it from the queue
    public void traverse () {
        if (isEmpty())
            System.out.println("Error: Queue Is Empty.");
        while (!isEmpty()) {
            PrintJob job = dequeue();
            System.out.println("Processing Print Job: {File Name = " + job.getFileName() + ",Pages Number = " + job.getPagesNumber() + "}");// Traversing through each print job in the queue and process it
            if (isEmpty())
                System.out.println("Print Jobs Processed Successfully, Queue Is Empty Now.");
        }
    }
    // d. Searching algorithm to search for a specific print job in the queue by its file name
    public PrintJob search (String File_Name) {
        for (PrintJob job : queue) {
            if (job.getFileName().equals(File_Name)) {
                System.out.println("Print Job Is Found: " + job);
                return job;
            }
        }
        System.out.println("Print Job Is Not Found!");
        return null;
    }
    // e. Sorting algorithm using the Merge Sorting implemented by using another method 'merge' and sorts the Print Jobs by highest priority which is for the file has the lowest Page Numbers
    public void sort() {
        // In case if the queue was empty and no print job within it
        if (isEmpty())
            System.out.println("There Is No Print Jobs To Sort, Queue Is Empty.");
        if (queue.size() > 1) {
            LinkedList<PrintJob> left = new LinkedList<>();
            LinkedList<PrintJob> right = new LinkedList<>();
            int middle = queue.size() / 2; // the middle index in the list to make sure where to divide the list to two halves
            for (int i = 0; i < middle; i++)
                left.add(queue.get(i)); // this loop to insert the print jobs on the left side at the middle
            for (int i = middle; i < queue.size(); i++)
                right.add(queue.get(i)); // this loop to insert the print jobs on the right side at the middle
            PrintQueue leftQueue = new PrintQueue();
            leftQueue.queue = left;
            PrintQueue rightQueue = new PrintQueue();
            rightQueue.queue = right;
            leftQueue.sort();
            rightQueue.sort();
            merge(leftQueue.queue, rightQueue.queue);
        }
    }
    // This private method created to merge two sorted lists into one sorted list
    private void merge(LinkedList<PrintJob> left, LinkedList<PrintJob> right) {
        queue.clear();
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.peek().getPagesNumber() <= right.peek().getPagesNumber())
                queue.add(left.poll());
             else
                queue.add(right.poll());
        }
        while (!left.isEmpty())
            queue.add(left.poll());
        while (!right.isEmpty())
            queue.add(right.poll());
    }
    // This method to print all print jobs in the queue
    public void displayQueue (){
        if (isEmpty())
            System.out.println("Queue Is Empty.");
        for (PrintJob job : queue)
            System.out.println(job);
        if (!isEmpty() && queue.size() > 1) {
            System.out.println(); // Print new empty line to make the output more clearly
            System.out.println("The Front (" + queue.getFirst()+")"); // To show the user the front print job in the queue
            System.out.println("The Rear ("+ queue.getLast()+")"); // To show the user the rear print job queue
        }
        else if (!isEmpty() && queue.size() == 1) {
            System.out.println(); // Print new empty line to make the output more clearly
            System.out.println("The Front & Rear (" + queue.peek() + ")"); // when there is one print job in the queue then rear = front
        }
    }
    // 'isEmpty' method to check if queue is empty or not
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Create the main class that contains the main method
public class Project {
    // main method begins execution of java application
    public static void main (String [] args) {
        Scanner input = new Scanner (System.in); // Create an object 'input' from class 'Scanner' to read an input from the user
        PrintQueue q = new PrintQueue (); // Create an object 'q' from class 'PrintQueue' to implement the algorithms
        // adding print jobs at the default queue
        q.enqueue(new PrintJob("IT231_Slides",100));
        q.enqueue(new PrintJob("IT232_Slides",200));
        q.enqueue(new PrintJob("IT233_Slides",300));
        System.out.println("Welcome to Print Job Management System, This is Your Current Print Job Queue:\n ");
        q.displayQueue(); // view the default queue to the user
        // This under Score line is to organize the code and make it more clearly
        System.out.println("___________________________________________________________________");
        System.out.println("Please Choose The Number For The Action That You Want To Perform on The Queue: ");
        // using while loop to show the menu again and again until the user end the program by choosing number 6 (Exit)
        while (true) {
            System.out.println("1. Insert New Print Job."); // Insertion
            System.out.println("2. Process The Front Print Job."); // Deletion
            System.out.println("3. Process All Print Jobs In The Queue."); // Traversal
            System.out.println("4. Search For Print Job."); // Searching
            System.out.println("5. Sort Print Jobs Based on Their Pages Number."); // Sorting (Merge Sorting)
            System.out.println("6. Exit.");
            int num = input.nextInt(); // Read the chosen number from the user
            switch (num) {
                // if the user chose number 1: Insert New Print Job.
                case 1:
                    System.out.println("Enter File Name: ");
                    String File_Name = input.next();
                    System.out.println("Enter Page Numbers: ");
                    int Pages_Number = input.nextInt();
                    q.enqueue(new PrintJob (File_Name,Pages_Number));
                    System.out.println("Print Job With File Name ("+ File_Name +") And Pages Numbers ("+ Pages_Number +") Inserted In The Queue Successfully.");
                    System.out.println("Queue Print Jobs After Inserting The New Print Job:");
                    System.out.println(); // Print new empty line to make the output more clearly
                    q.displayQueue(); // to show the user that the print job is inserted successfully
                    // This under Score line is to organize the code and make it more clearly
                    System.out.println("___________________________________________________________________");
                    System.out.println("Do you want to perform another action on the queue?");
                    break;
                // if the user chose number 2: Process The Front Print Job.
                case 2:
                    PrintJob job = q.dequeue();
                    if (job != null) {
                        System.out.println(job + " Processed and deleted from the queue.");
                        if (!q.isEmpty())
                            System.out.println("Queue Print Jobs After Processing The front Print Job:");
                        System.out.println(); // Print new empty line to make the output more clearly
                        q.displayQueue(); // to show the user that the print job is processed and deleted successfully
                    }
                    // This under Score line is to organize the code and make it more clearly
                    System.out.println("___________________________________________________________________");
                    System.out.println("Do you want to perform another action on the queue?");
                    break;
                // if the user chose number 3: Process All Print Jobs In The Queue.
                case 3:
                    if (!q.isEmpty()) {
                        System.out.println("Start Processing Print Jobs...");
                        System.out.println();
                    }
                    q.traverse();
                    // This under Score line is to organize the code and make it more clearly
                    System.out.println("___________________________________________________________________");
                    System.out.println("Do you want to perform another action on the queue?");
                    break;
                // if the user chose number 4: Search For Print Job.
                case 4:
                    System.out.print("Enter File Name To Search: ");
                    String searchFileName = input.next();
                    PrintJob searchResult = q.search(searchFileName);
                    // This under Score line is to organize the code and make it more clearly
                    System.out.println("___________________________________________________________________");
                    System.out.println("Do you want to perform another action on the queue?");
                    break;
                // if the user chose number 5: Sort Print Jobs Based on their Pages Number.
                case 5:
                    q.sort();
                    if (!q.isEmpty()) {
                        System.out.println("Print Jobs Sorted Successfully by Priority Based on Page Numbers.");
                        System.out.println("Print Jobs After Merge Sorting:");
                        System.out.println(); // Print new empty line to make the output more clearly
                        q.displayQueue(); // to show the user the queue after merge sorting process
                    }
                    // This under Score line is to organize the code and make it more clearly
                    System.out.println("___________________________________________________________________");
                    System.out.println("Do you want to perform another action on the queue?");
                    break;
                // if the user chose number 6: Exit. (To end the program)
                case 6:
                    System.out.println("Exiting...Thanks for using print job management system.");
                    input.close();
                    return;
                // if the user entered wrong number which is not from 1 to 6.
                default:
                    System.out.println("Invalid choice! Please try again.");
                    // This under Score line is to organize the code and make it more clearly
                    System.out.println("___________________________________________________________________");
            } // close brace for switch statement
        } // close brace for while statement
    } // close brace for main method
} // close brace for Project class