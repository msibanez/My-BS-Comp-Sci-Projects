import time
import random
import sys


# -- IMPORTANT NOTES --
# Credit Report types:
# 1 - loan amount is less than $2000
# 2 - type of loan is car, mortgage, or others
# 3 - type of loan is education; loan amount is between $2000 and $34999
# 4 - type of loan is education; loan amount is NOT between $2000 and $34999
#
# y/n = yes/no
# -----------------------------------------------------------------------------


# additional functions

# this function will be used to simulate a real life scenario
def getRandomTime(lower_ms, upper_ms):  # ms = milliseconds
    ms = random.randint(lower_ms, upper_ms)
    seconds = float(ms / 1000)

    return seconds


def displayErrorMessage():
    print("\nInvalid input.\n")
    time.sleep(2)


def displayFinalMessage():
    print("\nHave a good day, officer!")


# functions based on the logic diagram

def evaluateLoan(final_evaluation):  # approving or denying a loan
    print("\nThis loan is " + final_evaluation + ".")
    time.sleep(2)
    repeatEvaluation()


def sendToVicePresident():
    print("Waiting for a response from the vice president...\n")
    time.sleep(getRandomTime(7000, 11000))
    final_decision = random.randint(0, 1)  # 50/50 chance of either approving or denying the loan (like a coin toss)
    print("A final decision has been made:")
    time.sleep(2)

    if final_decision == 0:
        evaluateLoan("approved")
    else:
        evaluateLoan("denied")


def getLoan():
    print()

    while True:

        try:
            inputted_loan_amount = float(input("Enter loan amount (in dollars): "))

            if inputted_loan_amount > 0:
                loan_amount = round(inputted_loan_amount, 2)
                return loan_amount

            elif inputted_loan_amount == 0:
                print("\nInvalid amount. You can't loan nothing.\n")
                time.sleep(2)

            else:
                print("\nInvalid amount. Money can't be negative.\n")

        except:
            print("\nInvalid input. Numbers only.\n")


def checkLoanAmount(loan_amount):
    if loan_amount < 2000:
        checkCreditReport(1)

    elif 2000 <= loan_amount <= 200000:
        checkLoanType(loan_amount)

    else:
        print("\nThis loan application has been sent to the vice president for review and approval.\n")
        time.sleep(4)
        sendToVicePresident()


def checkCreditReport(type):
    print()

    while True:
        rating = input("What is the applicant's credit report?\n\n"
                       + "1 - Good/Excellent\n"
                       + "2 - Fair\n"
                       + "3 - Poor\n\n"
                       + "Enter number: ")

        if type == 1:
            if rating == "1":
                evaluateLoan("approved")

            elif rating == "2":
                checkIfAccountHolder()

        elif type == 2 and (rating == "1" or rating == "2"):
            verifySalary()

        elif type == 3 and (rating == "1" or rating == "2"):
            evaluateLoan("aprroved")

        else:  # (type == 4)
            if rating == "1":
                evaluateLoan("aprroved")
                
            elif rating == "2":
                evaluateLoan("denied")

        if rating == "3":
            evaluateLoan("denied")

        displayErrorMessage()


def checkIfAccountHolder():
    print()

    while True:
        isHolder = input("Is this applicant the account holder? (y/n)\n\n"
                         + "Enter answer: ")

        if isHolder == "y":
            evaluateLoan("approved")

        elif isHolder == "n":
            evaluateLoan("denied")

        else:
            displayErrorMessage()


def checkLoanType(loan_amount):
    print()

    while True:
        choice = input("Is this an education loan request? (y/n)\n\n"
                       + "Enter answer: ")

        if choice == "y":
            attendance_cost = getAttendanceCost()
            compareLoanAndAttendanceCost(loan_amount, attendance_cost)

        elif choice == "n":
            checkCreditReport(2)

        else:
            displayErrorMessage()


def verifySalary():
    print()

    while True:
        choice = input("Is the applicant's salary income verified? (y/n)\n\n"
                       + "Enter answer: ")

        if choice == "y":
            evaluateLoan("approved")

        elif choice == "n":
            print("\nContacting the applicant and requesting for additional information...\n")
            time.sleep(getRandomTime(3000, 6000))
            print("Received additional information.")
            time.sleep(2)
            print(
                "This has been sent, along with the loan application, to the vice president for review and approval.\n")
            time.sleep(4)
            sendToVicePresident()

        else:
            displayErrorMessage()


def getAttendanceCost():
    print()

    while True:
        try:
            attendance_cost = float(input("Enter attendance cost (in dollars): "))

            if attendance_cost < 0:
                print("\nInvalid amount. Money can't be negative.\n")

            else:
                return attendance_cost

        except:
            print("\nInvalid input. Numbers only.\n")


def compareLoanAndAttendanceCost(loan_amount, attendance_cost):
    if loan_amount > attendance_cost:
        evaluateLoan("denied")

    else:  # (loan_amount <= attendance_cost)
        analyzeLoanAmount(loan_amount)


def analyzeLoanAmount(loan_amount):
    if 2000 <= loan_amount <= 34999:
        checkCreditReport(3)
    else: 	# (loan_amount > 34999)
        checkCreditReport(4)


# the main functions where the code is to start

def evaluateApplication():

    loan_amount = getLoan()
    checkLoanAmount(loan_amount)


def main():
    print("\nGood day, officer! ", end='')

    while True:
        choice = input("What would you like to do?\n\n"
                       + "1 - Evaluate loan application\n"
                       + "2 - Exit\n\n"
                       + "Enter number: ")

        if choice == "1":
            evaluateApplication()
        elif choice == "2":
            displayFinalMessage()
            sys.exit()
        else:
            displayErrorMessage()


def repeatEvaluation():
    print("\n=========================\n End of Loan Application\n=========================\n")


    while True:
        choice = input("Would you like to do another evaluation? (y/n)\n\n"
                       + "Enter answer: ")

        if choice == "y":
            evaluateApplication()
        elif choice == "n":
            displayFinalMessage()
            sys.exit()
        else:
            displayErrorMessage()


main()
