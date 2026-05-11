package chatbotApp;

import java.util.Scanner;

public class SimpleChatbot {

    // Rule-based elementary chatbot for customer interaction
    // Uses simple keyword matching as a basic AI technique
    public static String getResponse(String message) {
        String text = message.toLowerCase().trim();

        // Greeting
        if (text.contains("hi") || text.contains("hello") || text.contains("hey")) {
            return "Hello! Welcome to Lenovo Store. How can I help you today?";
        }

        // Product information
        if (text.contains("product") || text.contains("laptop") || text.contains("phone") || text.contains("price")) {
            return "We have laptops starting from Rs. 40,000 and phones starting from Rs. 10,000.";
        }

        // Service details
        if (text.contains("service") || text.contains("repair") || text.contains("installation") || text.contains("support")) {
            return "Our services include free installation, 1-year warranty, and 24x7 customer support.";
        }

        // FAQ: delivery
        if (text.contains("delivery") || text.contains("shipping") || text.contains("when will my order arrive")) {
            return "Delivery usually takes 3 to 5 working days.";
        }

        // FAQ: payment
        if (text.contains("payment") || text.contains("upi") || text.contains("card") || text.contains("cash")) {
            return "We accept UPI, debit card, credit card, net banking, and cash on delivery.";
        }

        // FAQ: return/refund
        if (text.contains("return") || text.contains("refund") || text.contains("replace")) {
            return "You can return a product within 7 days with bill and original packaging.";
        }

        // FAQ: timing/location
        if (text.contains("time") || text.contains("timing") || text.contains("open") || text.contains("close")) {
            return "Our store is open from 10 AM to 8 PM, Monday to Saturday.";
        }

        if (text.contains("location") || text.contains("address") || text.contains("where")) {
            return "We are located near City Mall, Main Road.";
        }

        // Exit
        if (text.equals("bye") || text.equals("exit") || text.equals("quit")) {
            return "Thank you for contacting Lenovo Store. Have a nice day!";
        }

        // Default fallback
        return "Sorry, I did not understand. Please ask about products, services, delivery, payment, or returns.";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println(" Rule-Based Elementary Chatbot ");
        System.out.println("==============================");
        System.out.println("Type your message. Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = sc.nextLine();

            String response = getResponse(userInput);
            System.out.println("Bot: " + response);

            String check = userInput.toLowerCase().trim();
            if (check.equals("bye") || check.equals("exit") || check.equals("quit")) {
                break;
            }
        }

        sc.close();
    }
}

/*
================================================================================
                    RULE-BASED CHATBOT - DEFINITION & EXAMPLES
================================================================================

1. RULE-BASED CHATBOT DEFINITION:
   A rule-based chatbot is an AI system that responds to user input based on 
   predefined rules and patterns. It uses keyword matching and conditional logic
   to determine appropriate responses.

   Key Characteristics:
   - Uses IF-THEN rules (if user says X, respond with Y)
   - Pattern/keyword matching
   - No machine learning involved
   - Deterministic behavior (same input = same output)
   - Limited context understanding
   - Easy to implement and understand

2. ELEMENTARY CHATBOT DEFINITION:
   An elementary (or basic) chatbot is a simple conversational AI with minimal
   complexity. It typically handles basic queries and FAQs without advanced NLP
   or learning capabilities.

   Key Characteristics:
   - Handles simple, predefined conversations
   - Limited vocabulary
   - Surface-level understanding
   - No memory between conversations
   - Quick response time
   - Suitable for basic customer service

================================================================================
                              EXAMPLES IN THIS CODE:
================================================================================

EXAMPLE 1 - Greeting Rule:
Input:  "Hi there!"
Rule:   if text.contains("hi") OR text.contains("hello")
Output: "Hello! Welcome to Lenovo Store. How can I help you today?"

EXAMPLE 2 - Product Inquiry Rule:
Input:  "What is the price of a laptop?"
Rule:   if text.contains("product") OR text.contains("price") OR text.contains("laptop")
Output: "We have laptops starting from Rs. 40,000 and phones from Rs. 10,000."

EXAMPLE 3 - Delivery Question Rule:
Input:  "When will my order arrive?"
Rule:   if text.contains("delivery") OR text.contains("shipping")
Output: "Delivery usually takes 3 to 5 working days."

EXAMPLE 4 - Payment Options Rule:
Input:  "Can I pay with card?"
Rule:   if text.contains("payment") OR text.contains("card")
Output: "We accept UPI, debit card, credit card, net banking, and cash on delivery."

EXAMPLE 5 - Store Timing Rule:
Input:  "What are your working hours?"
Rule:   if text.contains("time") OR text.contains("open")
Output: "Our store is open from 10 AM to 8 PM, Monday to Saturday."

EXAMPLE 6 - Default Fallback Rule:
Input:  "Can you dance?"
Rule:   No keyword match found
Output: "Sorry, I did not understand. Please ask about products, services, etc."

================================================================================
                            FLOW DIAGRAM:
================================================================================

                          USER INPUT
                              |
                              v
                    Convert to Lowercase
                              |
                              v
                    Check Rule 1 (Greeting)
                    /            \
                  YES              NO
                  |                |
              Return            Check Rule 2 (Product)
              Response           /            \
                                YES            NO
                                |              |
                            Return          Check Rule 3 (Service)
                            Response        ... (Continue checking)
                                              |
                                              v
                                        Return Default Response

================================================================================
                        ADVANTAGES & LIMITATIONS:
================================================================================

ADVANTAGES:
✓ Simple to implement and understand
✓ Fast response times
✓ Predictable and reliable behavior
✓ No training data required
✓ Good for FAQs and simple queries
✓ Easy to debug and modify

LIMITATIONS:
✗ Cannot understand complex sentences
✗ Requires manual rule creation
✗ Cannot learn from conversations
✗ Limited flexibility
✗ Poor context understanding
✗ Fails with spelling variations not in rules
✗ Scalability issues with large rule sets

================================================================================
*/
