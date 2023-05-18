import sys
import random
import sqlite3 as sql
import locale as lo
import math
import time
import pandas as pd

lo.setlocale(lo.LC_ALL, 'en_US.UTF-8')

num = (0,1,2,3,4,5,6,7,8,9)
num2 = (1,2,3)

money = (1000, 2000, 4000, 8000, 15000, 30000, 40000, 50000, 80000, 100000, 200000, 300000, 600000, 800000, 1000000)

conn = sql.connect("questions.db")
c = conn.cursor()

conn2 = sql.connect("highscores.db")
c2 = conn2.cursor()

def wrong_letter(letter):

	letter_chooser = random.randint(1,3)
	c1 = "A"
	c2 = "B"
	c3 = "C"
	c4 = "D"

	if (letter == "A"):
		if (letter_chooser == 1):
			return c2
		elif (letter_chooser == 2):
			return c3
		else:
			return c4
	elif(letter == "B"):
		if (letter_chooser == 1):
			return c1
		elif (letter_chooser == 2):
			return c3
		else:
			return c4
	elif(letter == "C"):
		if (letter_chooser == 1):
			return c1
		elif (letter_chooser == 2):
			return c2
		else:
			return c4
	elif(letter == "D"):
		if (letter_chooser == 1):
			return c1
		elif (letter_chooser == 2):
			return c2
		else:
			return c3


def convertTuple(tup): 
    str =  ''.join(tup) 
    return str

def main():

	life_friend = 0
	life_50 = 0

	r1 = 0
	r2 = 0
	r3 = 0
	r4 = 0

	n = random.sample(num, 5)
	o = random.sample(num, 5)
	p = random.sample(num, 5)

	print(""), print("================================"), print(" WHO WANTS TO BE A MILLIONAIRE?"), print("================================")
	time.sleep(0.25)

	while(True):
		print("")
		print("1 - Play the game"), time.sleep(0.25), print("2 - View the highscores"), time.sleep(0.25), print("3 - Quit game"), time.sleep(0.25), print("")
		i = input("Enter number: ")

		if(i == "1"):
			print("")
			user = input("Enter your name: ")
			print("")
			print("Welcome to the game,",user, end=""),print("!")
			print("-There are 15 questions. 5 each for easy, moderate, and hard rounds.")
			print("-Your answers can be in either CAPITAL or LOWERCASE letters from A-D only.")
			print("-Inputting anything else or an incorrect answer will mark it wrong.")
			print("-Wrong answers will give you 1/4 of your current cash prize.")
			print("-There are two lifelines that you can only use once for each.")
			print("-You can't use a lifeline in the final question.")
			print("")
			print("Are you ready?")
			input("Press Enter if you are ready!")

			firstdone = 0

###	=====================================================================================================

			print("")
			print("============")
			print(" EASY ROUND")
			print("============")
			print("")

			c.execute("SELECT Question FROM easy")
			data1 = c.fetchall()
			c.execute("SELECT Choice1 FROM easy")
			data2 = c.fetchall()
			c.execute("SELECT Choice2 FROM easy")
			data3 = c.fetchall()
			c.execute("SELECT Choice3 FROM easy")
			data4 = c.fetchall()
			c.execute("SELECT Choice4 FROM easy")
			data5 = c.fetchall()
			c.execute("SELECT Correct FROM easy")
			data6 = c.fetchall()

			time.sleep(1.5)

###	=====================================================================================================

			for j in range(0,5):

				while(True):

					corr = convertTuple(data6[n[j]])

					print("",j+1, end=""),print(". ", end=""), print(*data1[n[j]], sep=' ')
					print("")
					if(r1 == 0):
						print(*data2[n[j]], sep=' ')
					if(r2 == 0):
						print(*data3[n[j]], sep=' ')
					if(r3 == 0):
						print(*data4[n[j]], sep=' ')
					if(r4 == 0):
						print(*data5[n[j]], sep=' ')
					time.sleep(1)

					print("")
					print("----------------------------------------")
					print("")
					print("What will you to do?")
					print("")
					print("1 - Choose the answer to the question")
					print("2 - Use lifeline")
					print("3 - Walk away with 1/2 of your current earnings")
					print("")
					choice = input("Enter the number: ")
					print("")

					if (choice == "1"):

						answer = input("Enter your answer: ")
						answer = answer.upper()
						print("")

						if (answer == corr):
							print("The answer is correct! You earned ${}!".format(f'{money[j]:n}'))
							answer = None
							print("Do you want to continue the game or walk away with your current earnings?")
							print("")
							print("1 - CONTINUE")
							print("2 - QUIT. GIVE ME THE MONEY!")
							print("")

							r1 = 0
							r2 = 0
							r3 = 0
							r4 = 0

							while(True):
								takehome = input("Enter the number: ")
								if(takehome == "2"):
									print("")
									print("Thanks for playing, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{money[j]:n}'))

									val = (user, money[j])
									c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
									conn2.commit()

									answer = None
									print("")
									input("Press Enter to go back to the main menu...")
									main()

								elif(takehome == "1"):
									print("")
									print("-------------------------------------------------")
									print("")
									break
								else:
									print("")
									print("*** Please choose between 1 or 2 only ***")
									time.sleep(1.5)
									print("")
							firstdone = 1
							break

						else:
							if(firstdone == 1):
								quartmoney = money[j-1] / 4
								print("Sorry, that's the wrong answer!")
								print("Better luck next time, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{quartmoney:n}'))
								
								val = (user, int(quartmoney))
								c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
								conn2.commit()

								answer = None
								print("")
								input("Press Enter to go back to the main menu...")
								main()
							else:
								print("Sorry, that's the wrong answer!")
								print("Better luck next time, {}!".format(user), end=" "), print("You didn't earn any money!")
								answer = None
								print("")
								input("Press Enter to go back to the main menu...")
								main()

					elif (choice == "2"):

						print("Which lifeline will you use? (one use each)")
						print("")
						print("1 - Call a friend")
						print("2 - 50/50")
						print("")

						while(True):
							life_choice = input("Enter the number: ")
							print("")
							if(life_choice == "1"):
								if(life_friend == 0):
									
									friend_chooser = random.randint(1,3)
									friend_correct = random.randint(1,10)

									if(friend_chooser == 1):
										if(friend_correct <= 9):
											print("*** Your wise friend believes that the correct answer is", corr, end=""), print(". ***")
											time.sleep(2)
											print("")
											print("------------------------------------------------------------")
											print("")
										else:
											print("*** Your wise friend believes that the correct answer is", wrong_letter(corr), end=""), print(". ***")
											time.sleep(2)
											print("")
											print("------------------------------------------------------------")
											print("")

									if(friend_chooser == 2):
										if(friend_correct <= 5):
											print("*** Your unsure friend thinks that the correct answer is probably", corr, end=""), print(". ***")
											time.sleep(2)
											print("")
											print("-----------------------------------------------------------------------")
											print("")
										else:
											print("*** Your unsure friend thinks that the correct answer is probably", wrong_letter(corr), end=""), print(". ***")
											time.sleep(2)
											print("")
											print("-----------------------------------------------------------------------")
											print("")

									if(friend_chooser == 3):
										if(friend_correct <= 3):
											print("*** Your arrogant friend confidently says the correct answer is", corr, end=""), print(". ***")
											time.sleep(2)
											print("")
											print("---------------------------------------------------------------------")
											print("")

										else:
											print("*** Your arrogant friend confidently says the correct answer is", wrong_letter(corr), end=""), print(". ***")
											time.sleep(2)
											print("")
											print("---------------------------------------------------------------------")
											print("")

									life_friend = 1
									break
								else:
									print('*** You already used the "call a frined" lifeline. ***')
									time.sleep(2)
									print("")
									print("------------------------------------------------------")
									print("")
									break

							elif(life_choice == "2"):
								if(life_50 == 0):

									if (corr == "A" or corr == "B"):
										r3 = 1
										r4 = 1

									if (corr == "C" or corr == "D"):
										r1 = 1
										r2 = 1

									life_50 = 1

									print('*** Removing two incorrect answers... ***')
									time.sleep(2)
									print("")
									print("------------------------------------------")
									print("")
									break
								else:
									print('*** You already used the "50/50" lifeline. ***')
									time.sleep(2)
									print("")
									print("----------------------------------------------")
									print("")
									break

							else:
								print("")
								print("*** Please choose between 1 or 2 only ***")
								time.sleep(1.5)
								print("")

					elif (choice == "3"):

						if(firstdone == 1):
							halfmoney = money[j-1] / 2
							print("Thanks for playing, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{halfmoney:n}'))
							
							val = (user, int(halfmoney))
							c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
							conn2.commit()
							print("")
							input("Press Enter to go back to the main menu...")
							main()
						else:
							print("*** You can't do this at the first question! ***")
							time.sleep(2.5)
							print("")
							print("-------------------------------------------------")
							print("")

					else:
					
						print("*** Please choose between 1-3 only. ***")
						time.sleep(2)
						print("")
						print("---------------------------------------------------------------------")
						print("")

###	=====================================================================================================

			print("================")
			print(" MODERATE ROUND")
			print("================")
			print("")

			c.execute("SELECT Question FROM moderate")
			data1 = c.fetchall()
			c.execute("SELECT Choice1 FROM moderate")
			data2 = c.fetchall()
			c.execute("SELECT Choice2 FROM moderate")
			data3 = c.fetchall()
			c.execute("SELECT Choice3 FROM moderate")
			data4 = c.fetchall()
			c.execute("SELECT Choice4 FROM moderate")
			data5 = c.fetchall()
			c.execute("SELECT Correct FROM moderate")
			data6 = c.fetchall()

			time.sleep(1.5)

###	=====================================================================================================

			for j in range(0,5):

				while(True):

					corr = convertTuple(data6[o[j]])

					print("",j+6, end=""),print(". ", end=""), print(*data1[o[j]], sep=' ')
					print("")
					if(r1 == 0):
						print(*data2[o[j]], sep=' ')
					if(r2 == 0):
						print(*data3[o[j]], sep=' ')
					if(r3 == 0):
						print(*data4[o[j]], sep=' ')
					if(r4 == 0):
						print(*data5[o[j]], sep=' ')
					time.sleep(1)

					print("")
					print("----------------------------------------")
					print("")
					print("What will you to do?")
					print("")
					print("1 - Choose the answer to the question")
					print("2 - Use lifeline")
					print("3 - Walk away with 1/2 of your current earnings")
					print("")
					choice = input("Enter the number: ")

					if (choice == "1"):

						confirm = 0
						print("")
						answer = input("Enter your answer: ")
						whatever_case = answer
						answer = answer.upper()
						print("")
						print("---------------------------")
						print("Is this your final answer?")
						print("Your answer:", whatever_case)
						print("---------------------------")
						print("")
						print("1 - YES")
						print("2 - NO")
						print("")
						while(True):
							final_answer = input("Enter confirmation number: ")
							if (final_answer == "1"):
								print("")
								confirm = 1
								break

							elif(final_answer == "2"):
								print("")
								print("----------------------------------------")
								print("")
								break
							else:
								print("")
								print("*** Please choose between 1 or 2 only ***")
								time.sleep(1.5)
								print("")

						if (confirm == 1):
							if (answer == corr):
								print("The answer is correct! You earned ${}!".format(f'{money[j+5]:n}'))
								answer = None
								print("Do you want to continue the game or walk away with your current earnings?")
								print("")
								print("1 - CONTINUE")
								print("2 - QUIT. GIVE ME THE MONEY!")
								print("")

								r1 = 0
								r2 = 0
								r3 = 0
								r4 = 0

								while(True):
									takehome = input("Enter the number: ")
									if(takehome == "2"):
										print("")
										print("Thanks for playing, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{money[j+5]:n}'))

										val = (user, money[j+5])
										c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
										conn2.commit()

										answer = None
										print("")
										input("Press Enter to go back to the main menu...")
										main()

									elif(takehome == "1"):
										print("")
										print("-------------------------------------------------")
										print("")
										break
									else:
										print("")
										print("*** Please choose between 1 or 2 only ***")
										time.sleep(1.5)
										print("")
								break	

							else:

								quartmoney = money[j+4] / 4
								print("Sorry, that's the wrong answer!")
								print("Better luck next time, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{quartmoney:n}'))
							
								val = (user, int(quartmoney))
								c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
								conn2.commit()

								answer = None
								print("")
								input("Press Enter to go back to the main menu...")
								main()

					elif (choice == "2"):

						print("Which lifeline will you use? (one use each)")
						print("")
						print("1 - Call a friend")
						print("2 - 50/50")
						print("")

						while(True):
							life_choice = input("Enter the number: ")
							print("")
							if(life_choice == "1"):
								if(life_friend == 0):
									
									friend_chooser = random.randint(1,3)
									friend_correct = random.randint(1,10)

									if(friend_chooser == 1):
										if(friend_correct <= 9):
											print("*** Your wise friend believes that the correct answer is", corr, end=""), print(". ***")
											time.sleep(2)
											print("")
											print("------------------------------------------------------------")
											print("")
										else:
											print("*** Your wise friend believes that the correct answer is", wrong_letter(corr), end=""), print(". ***")
											time.sleep(2)
											print("")
											print("------------------------------------------------------------")
											print("")

									if(friend_chooser == 2):
										if(friend_correct <= 5):
											print("*** Your unsure friend thinks that the correct answer is probably", corr, end=""), print(". ***")
											time.sleep(2)
											print("")
											print("-----------------------------------------------------------------------")
											print("")
										else:
											print("*** Your unsure friend thinks that the correct answer is probably", wrong_letter(corr), end=""), print(". ***")
											time.sleep(2)
											print("")
											print("-----------------------------------------------------------------------")
											print("")

									if(friend_chooser == 3):
										if(friend_correct <= 3):
											print("*** Your arrogant friend confidently says the correct answer is", corr, end=""), print(". ***")
											time.sleep(2)
											print("")
											print("---------------------------------------------------------------------")
											print("")

										else:
											print("*** Your arrogant friend confidently says the correct answer is", wrong_letter(corr), end=""), print(". ***")
											time.sleep(2)
											print("")
											print("---------------------------------------------------------------------")
											print("")

									life_friend = 1
									break
								else:
									print('*** You already used the "call a frined" lifeline. ***')
									time.sleep(2)
									print("")
									print("------------------------------------------------------")
									print("")
									break

							elif(life_choice == "2"):
								if(life_50 == 0):

									if (corr == "A"):
										r3 = 1
										r4 = 1

									if (corr == "B"):
										r3 = 1
										r4 = 1

									if (corr == "C"):
										r1 = 1
										r2 = 1
										
									if (corr == "D"):
										r1 = 1
										r2 = 1

									life_50 = 1

									print('*** Removing two incorrect answers... ***')
									time.sleep(2)
									print("")
									print("------------------------------------------")
									print("")
									break
								else:
									print('*** You already used the "50/50" lifeline. ***')
									time.sleep(2)
									print("")
									print("----------------------------------------------")
									print("")
									break

							else:
								print("")
								print("*** Please choose between 1 or 2 only ***")
								time.sleep(1.5)
								print("")

					elif (choice == "3"):

						halfmoney = money[j+4] / 2
						print("Thanks for playing, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{halfmoney:n}'))
							
						val = (user, int(halfmoney))
						c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
						conn2.commit()
						print("")
						input("Press Enter to go back to the main menu...")
						main()

					else:
					
						print("*** Please choose between 1-3 only. ***")
						time.sleep(2)
						print("")
						print("---------------------------------------------------------------------")
						print("")

###	=====================================================================================================

			print("============")
			print(" HARD ROUND")
			print("============")
			print("")

			c.execute("SELECT Question FROM hard")
			data1 = c.fetchall()
			c.execute("SELECT Choice1 FROM hard")
			data2 = c.fetchall()
			c.execute("SELECT Choice2 FROM hard")
			data3 = c.fetchall()
			c.execute("SELECT Choice3 FROM hard")
			data4 = c.fetchall()
			c.execute("SELECT Choice4 FROM hard")
			data5 = c.fetchall()
			c.execute("SELECT Correct FROM hard")
			data6 = c.fetchall()

			time.sleep(1.5)

###	=====================================================================================================

			for j in range(0,4):

				while(True):

					corr = convertTuple(data6[p[j]])

					print("",j+11, end=""),print(". ", end=""), print(*data1[p[j]], sep=' ')
					print("")
					if(r1 == 0):
						print(*data2[p[j]], sep=' ')
					if(r2 == 0):
						print(*data3[p[j]], sep=' ')
					if(r3 == 0):
						print(*data4[p[j]], sep=' ')
					if(r4 == 0):
						print(*data5[p[j]], sep=' ')
					time.sleep(1)

					print("")
					print("----------------------------------------")
					print("")
					print("What will you to do?")
					print("")
					print("1 - Choose the answer to the question")
					print("2 - Use lifeline")
					print("3 - Walk away with 1/2 of your current earnings")
					print("")
					choice = input("Enter the number: ")

					if (choice == "1"):

						confirm = 0
						print("")
						answer = input("Enter your answer: ")
						whatever_case = answer
						answer = answer.upper()
						print("")
						print("---------------------------")
						print("Is this your final answer?")
						print("Your answer:", whatever_case)
						print("---------------------------")
						print("")
						print("1 - YES")
						print("2 - NO")
						print("")
						while(True):
							final_answer = input("Enter confirmation number: ")
							if (final_answer == "1"):
								print("")
								confirm = 1
								break

							elif(final_answer == "2"):
								print("")
								print("----------------------------------------")
								print("")
								break
							else:
								print("")
								print("*** Please choose between 1 or 2 only ***")
								time.sleep(1.5)
								print("")

						if (confirm == 1):
							if (answer == corr):
								print("The answer is correct! You earned ${}!".format(f'{money[j+10]:n}'))
								answer = None
								print("Do you want to continue the game or walk away with your current earnings?")
								print("")
								print("1 - CONTINUE")
								print("2 - QUIT. GIVE ME THE MONEY!")
								print("")

								r1 = 0
								r2 = 0
								r3 = 0
								r4 = 0

								while(True):
									takehome = input("Enter the number: ")
									if(takehome == "2"):
										print("")
										print("Thanks for playing, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{money[j+10]:n}'))

										val = (user, money[j+10])
										c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
										conn2.commit()

										answer = None
										print("")
										input("Press Enter to go back to the main menu...")
										main()

									elif(takehome == "1"):
										print("")
										print("-------------------------------------------------")
										print("")
										break
									else:
										print("")
										print("*** Please choose between 1 or 2 only ***")
										time.sleep(1.5)
										print("")
								break	

							else:

								quartmoney = money[j+9] / 4
								print("Sorry, that's the wrong answer!")
								print("Better luck next time, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{quartmoney:n}'))
							
								val = (user, int(quartmoney))
								c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
								conn2.commit()

								answer = None
								print("")
								input("Press Enter to go back to the main menu...")
								main()

					elif (choice == "2"):

						print("Which lifeline will you use? (one use each)")
						print("")
						print("1 - Call a friend")
						print("2 - 50/50")
						print("")

						while(True):
							life_choice = input("Enter the number: ")
							print("")
							if(life_choice == "1"):
								if(life_friend == 0):
									
									friend_chooser = random.randint(1,3)
									friend_correct = random.randint(1,10)

									if(friend_chooser == 1):
										if(friend_correct <= 9):
											print("*** Your wise friend believes that the correct answer is", corr, end=""), print(". ***")
											time.sleep(2)
											print("")
											print("------------------------------------------------------------")
											print("")
										else:
											print("*** Your wise friend believes that the correct answer is", wrong_letter(corr), end=""), print(". ***")
											time.sleep(2)
											print("")
											print("------------------------------------------------------------")
											print("")

									if(friend_chooser == 2):
										if(friend_correct <= 5):
											print("*** Your unsure friend thinks that the correct answer is probably", corr, end=""), print(". ***")
											time.sleep(2)
											print("")
											print("-----------------------------------------------------------------------")
											print("")
										else:
											print("*** Your unsure friend thinks that the correct answer is probably", wrong_letter(corr), end=""), print(". ***")
											time.sleep(2)
											print("")
											print("-----------------------------------------------------------------------")
											print("")

									if(friend_chooser == 3):
										if(friend_correct <= 3):
											print("*** Your arrogant friend confidently says the correct answer is", corr, end=""), print(". ***")
											time.sleep(2)
											print("")
											print("---------------------------------------------------------------------")
											print("")

										else:
											print("*** Your arrogant friend confidently says the correct answer is", wrong_letter(corr), end=""), print(". ***")
											time.sleep(2)
											print("")
											print("---------------------------------------------------------------------")
											print("")

									life_friend = 1
									break
								else:
									print('*** You already used the "call a frined" lifeline. ***')
									time.sleep(2)
									print("")
									print("------------------------------------------------------")
									print("")
									break

							elif(life_choice == "2"):
								if(life_50 == 0):

									if (corr == "A"):
										r3 = 1
										r4 = 1

									if (corr == "B"):
										r3 = 1
										r4 = 1

									if (corr == "C"):
										r1 = 1
										r2 = 1
										
									if (corr == "D"):
										r1 = 1
										r2 = 1

									life_50 = 1

									print('*** Removing two incorrect answers... ***')
									time.sleep(2)
									print("")
									print("------------------------------------------")
									print("")
									break
								else:
									print('*** You already used the "50/50" lifeline. ***')
									time.sleep(2)
									print("")
									print("----------------------------------------------")
									print("")
									break

							else:
								print("")
								print("*** Please choose between 1 or 2 only ***")
								time.sleep(1.5)
								print("")

					elif (choice == "3"):

						halfmoney = money[j+9] / 2
						print("Thanks for playing, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{halfmoney:n}'))
							
						val = (user, int(halfmoney))
						c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
						conn2.commit()
						print("")
						input("Press Enter to go back to the main menu...")
						main()

					else:
					
						print("*** Please choose between 1-3 only. ***")
						time.sleep(2)
						print("")
						print("---------------------------------------------------------------------")
						print("")

###	=====================================================================================================

			print("================")
			print(" FINAL QUESTION")
			print("================")
			print("")
			time.sleep(1.5)

###	=====================================================================================================

			for j in range(4,5):

				while(True):

					corr = convertTuple(data6[p[j]])

					print("15. ", end=""), print(*data1[p[j]], sep=' ')
					print("")
					print(*data2[p[j]], sep=' ')
					print(*data3[p[j]], sep=' ')
					print(*data4[p[j]], sep=' ')
					print(*data5[p[j]], sep=' ')
					time.sleep(2)

					print("")
					print("----------------------------------------")
					print("")
					print("What will you to do?")
					print("")
					print("1 - Choose the answer to the question")
					print("2 - Walk away with 1/2 of your current earnings")
					print("")
					choice = input("Enter the number: ")

					if (choice == "1"):

						confirm = 0
						print("")
						answer = input("Enter your answer: ")
						whatever_case = answer
						answer = answer.upper()
						print("")
						print("---------------------------")
						print("Is this your final answer?")
						print("Your answer:", whatever_case)
						print("---------------------------")
						print("")
						print("1 - YES")
						print("2 - NO")
						print("")
						while(True):
							final_answer = input("Enter confirmation number: ")
							if (final_answer == "1"):
								print("")
								confirm = 1
								break

							elif(final_answer == "2"):
								print("")
								print("----------------------------------------")
								print("")
								break
							else:
								print("")
								print("*** Please choose between 1 or 2 only ***")
								time.sleep(1.5)
								print("")

						if (confirm == 1):
							if (answer == corr):
								print("The answer is correct!")
								time.sleep(2)
								print("")
								print("===========================")
								print("")
								print(" CONGRATULATIONS, {}!".format(user.upper()))
								print(" YOU JUST WON $1,000,000!")
								print("")
								print("===========================")
								print("")

								val = (user, int(money[j+10]))
								c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
								conn2.commit()								

								input("Press Enter to go back to the main menu...")
								main()

							else:

								quartmoney = money[j+9] / 4
								print("Sorry, that's the wrong answer!")
								print("Better luck next time, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{quartmoney:n}'))
							
								val = (user, int(quartmoney))
								c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
								conn2.commit()

								answer = None
								print("")
								input("Press Enter to go back to the main menu...")
								main()

					elif (choice == "2"):

						halfmoney = money[j+9] / 2
						print("Thanks for playing, {}!".format(user), end=" "), print("You will be taking home ${}!".format(f'{halfmoney:n}'))
							
						val = (user, int(halfmoney))
						c2.execute("INSERT INTO leaderboard (Name, Score) VALUES (?, ?)", val)
						conn2.commit()

						print("")
						input("Press Enter to go back to the main menu...")
						main()

					else:
					
						print("*** Please choose between 1 or 2 only. ***")
						time.sleep(2)
						print("")
						print("---------------------------------------------------------------------")
						print("")

###	=====================================================================================================

		elif(i == "2"):

			sql_query = pd.read_sql_query("SELECT Name, Score FROM leaderboard ORDER BY CAST(Score AS INTEGER) DESC, Name ASC", conn2)
			df = pd.DataFrame(sql_query, columns=['Name','Score'])

			if (df.empty == True):
				print("")
				print("No scores available.") 
			else:
				df.index += 1
				df2 = df.head(n=10)
				print("")
				print("============")
				print(" Highscores")
				print("============")
				print("")
				print(df2)
				print("")
				print("------------------------")

		elif(i == "reset"):

			c2.execute("DELETE FROM leaderboard")
			conn2.commit()

			print("")
			print("Highscore database has been wiped out!")

		elif(i == "3"):
			print("")
			print("Thanks for playing!")

			conn.close()
			conn2.close()

			sys.exit()
		else:
			print("")
			print("*** Please choose between 1-3 only! ***")
			time.sleep(1.5)

main()