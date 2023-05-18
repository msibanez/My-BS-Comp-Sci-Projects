var op1 = "A"
var op2 = "A"

let period_exist = false
let operator_exist = false
let pressed_once = false
let operator_active = false
let equal_pressed = false
let operator_equal = true

let limit = 13

var operator_type

function smile() {
  var cool = document.getElementById('num').textContent
  document.getElementById('e').innerHTML = ""
  if (cool == 69) {
    document.getElementById('num').innerHTML = "             ;)"
  } else if (cool == 420) {
    document.getElementById('num').innerHTML = "       blaze it"
  } else if (cool == 666) {




    document.getElementById('num').innerHTML = "I̸̲͒̆̉̿ ̴̢̧̯̜̖͓͎̭̙̝̪̳̼̟̻̗̏̎̐͂̀͑̃̇͛͐͜͝s̶̨̢̛̛̱̩̣̗̠̟̠͎̱͇̩̥͇̯͚̮̞̬͓͎͕͔̟̩͉͕̺͎͎̜̳̟̯̼̫͖̐̈̃̓̀͋̽̓̾̈́̈̽̊͐͑̂̀̔̆̄́̄̍̚͘̕͘͜͜͜͠ͅè̷̡̘͎̘̙͖̙̱̞̞̯̤̯̦̮̳̮͚͖̺͓̰̱͎̝̤̦̳̣̭̫͍͂̎̓̔͐͋̌̌̽̈́̏̿͌̇͛͊͑̂́̓̊̊̓̀̐̾̏͋̾̂̐̐̔̂̈́͒͗̃̚͝ͅe̴̡̢͓̺͕̻̬̥̝͚̖̳̥̗͓͉̳̫̠̣̯̻̩̹̳̤͖̞̼͍̹͛̽̈́̇̏͌̐ͅ ̵̛̞̟̜͎̠͍̒͛̆̐̓̀͛͛͌͛̊̇̆͌̍̓̽̌̽̇̚͝͝͠͝d̶̡͓͓̟͈͙͇͓̪͔̋̎̓͑̓̾̓͐̀̌̾̋̊̈́̒̃̽̏̅̀̿̀̈͛͒̇̍̌̇̽̈́́̽̏̀̿̀̚͝͝e̶̢̢̢̨̘͎̯̲͕̺̤̻͇̺̙̬͓̩̲̖͓͎̗̱͎͚͉͈̞͕̲̻̦̺̞̠̫͓͚̦͍̦̍́̓̌̏̓̓̈́̎̈́̆͊͋̒̇͝a̴̢̛̛̛̦̙̳̞̩̜̺͋̇̀̄͗͂́͑͌͗͊͋͐̓̓̐̍̊̇̈́̊́͗̋̀̑͒͋͆́̄̀̾͊͐̎͋͐̈̿̿̕̚̚͘͝͝ͅḑ̷̢̛̛̛̛̮̟̦̲̣̰̼̈́͆͂̈́͌͂́͋̄̓̾̓̀͋͑́̅͊̐̓̇̎́̽͗̅̀̌̅͌̆̂̀̓̋͗̋͘̚͝͝͝͝ͅ ̴͙̙̯̮̗͐̓̈́͘p̸̡̢̧̡̛̥̦͕̙͎͙͖̲̙̟̘͈͚̱͍̥͕̟͓̼̪͔͇̘̳͕̘̞̙̫͓̲̳̫͛̾͐͋͂͐̂̂̋̂͋̄͊͋͂̆͐̌̊́͐͐͒̽͊̄̓͋͒̒̆̐͊̀̌̈́̕̕͘̚͜͠e̷̬̬͕̱͕͓̜̼̹̲̖̭̓́ō̴̳̩̙̠͖̝͓̠͖̠̪̮̼̉̾̚̚͜͝p̵̧̧͎̲̞̪͕̰͕̗̪̝̻̬͎̥͗̉̎̋͆̎̎̏̒̆̀̃̿̅͐͐͛̋̀̾̅͋̎̊̅̾̅̚͜͠l̴̨̡̡̟̯͖̻̖̹̜̼͚̹̹̪͇͇͔̤̣͔̰̯̲̯̞̜̉̔̀͐̀̃̃̀́̈͋̈́͑̌̒͂̏͐̿̃̒̀̏́͗̇̀͒͛͊̓̕͘̕͜è̷̛͇̓͋̃͐̍̐̀͊͗̊̀̆̓͐̈́̍̆̈́͋͗͛͋̄͌̂̌̒͐̊̃̿̔͌̓̚͠͝͝͝͠.̴̛̛̛̗̫́̀̀͋̎̈̇̌̽̏̒̆͋͗̉̔́̒̓͐͂̆̚̕̕̕͘͝͝͝͠͝͝"




  } else if (cool == 6 || cool == 28 || cool == 496 || cool == 8128
    || cool == 33550336 || cool == 8589869056 || cool == 137438691328) {
    document.getElementById('num').innerHTML = "    perfection"
  } else {
    document.getElementById('num').innerHTML = "happy happy :)"
  }
}

// numbers

function zero() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (num != "0") {
      if (ifError != 0 || ifInfinity != 0) {
        if (operator_exist) {
          operator_equal = false
          if (op2.length < limit || operator_active) {
            if (operator_active) {
              op2 = "0"
              document.getElementById('num').innerHTML = 0
              operator_active = false
            } else {
              op2 = document.getElementById('num').textContent
              op2 += 0
              document.getElementById('num').innerHTML = op2
            }
          }
        } else {
          var num2 = document.getElementById('num').textContent
          op1 = num2 + 0
          document.getElementById('num').innerHTML = op1
        }
      }
    }
  }
}

function one() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (ifError != 0 || ifInfinity != 0) {
      if (operator_exist) {
        operator_equal = false
        if (op2.length < limit || operator_active) {
          if (operator_active) {
            op2 = "1"
            document.getElementById('num').innerHTML = 1
            operator_active = false
          } else {
            op2 = document.getElementById('num').textContent
            op2 += 1
            document.getElementById('num').innerHTML = op2
          }
        }
      } else if (num == "0") {
          document.getElementById('num').innerHTML = 1
      } else {
        var num2 = document.getElementById('num').textContent
        op1 = num2 + 1
        document.getElementById('num').innerHTML = op1
      }
    }
  }
}

function two() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (ifError != 0 || ifInfinity != 0) {
      if (operator_exist) {
        operator_equal = false
        if (op2.length < limit || operator_active) {
          if (operator_active) {
            op2 = "2"
            document.getElementById('num').innerHTML = 2
            operator_active = false
          } else {
            op2 = document.getElementById('num').textContent
            op2 += 2
            document.getElementById('num').innerHTML = op2
          }
        }
      } else if (num == "0") {
          document.getElementById('num').innerHTML = 2
      } else {
        var num2 = document.getElementById('num').textContent
        op1 = num2 + 2
        document.getElementById('num').innerHTML = op1
      }
    }
  }
}

function three() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (ifError != 0 || ifInfinity != 0) {
      if (operator_exist) {
        operator_equal = false
        if (op2.length < limit || operator_active) {
          if (operator_active) {
            op2 = "3"
            document.getElementById('num').innerHTML = 3
            operator_active = false
          } else {
            op2 = document.getElementById('num').textContent
            op2 += 3
            document.getElementById('num').innerHTML = op2
          }
        }
      } else if (num == "0") {
          document.getElementById('num').innerHTML = 3
      } else {
        var num2 = document.getElementById('num').textContent
        op1 = num2 + 3
        document.getElementById('num').innerHTML = op1
      }
    }
  }
}

function four() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (ifError != 0 || ifInfinity != 0) {
      if (operator_exist) {
        operator_equal = false
        if (op2.length < limit || operator_active) {
          if (operator_active) {
            op2 = "4"
            document.getElementById('num').innerHTML = 4
            operator_active = false
          } else {
            op2 = document.getElementById('num').textContent
            op2 += 4
            document.getElementById('num').innerHTML = op2
          }
        }
      } else if (num == "0") {
          document.getElementById('num').innerHTML = 4
      } else {
        var num2 = document.getElementById('num').textContent
        op1 = num2 + 4
        document.getElementById('num').innerHTML = op1
      }
    }
  }
}

function five() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (ifError != 0 || ifInfinity != 0) {
      if (operator_exist) {
        operator_equal = false
        if (op2.length < limit || operator_active) {
          if (operator_active) {
            op2 = "5"
            document.getElementById('num').innerHTML = 5
            operator_active = false
          } else {
            op2 = document.getElementById('num').textContent
            op2 += 5
            document.getElementById('num').innerHTML = op2
          }
        }
      } else if (num == "0") {
          document.getElementById('num').innerHTML = 5
      } else {
        var num2 = document.getElementById('num').textContent
        op1 = num2 + 5
        document.getElementById('num').innerHTML = op1
      }
    }
  }
}

function six() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (ifError != 0 || ifInfinity != 0) {
      if (operator_exist) {
        operator_equal = false
        if (op2.length < limit || operator_active) {
          if (operator_active) {
            op2 = "6"
            document.getElementById('num').innerHTML = 6
            operator_active = false
          } else {
            op2 = document.getElementById('num').textContent
            op2 += 6
            document.getElementById('num').innerHTML = op2
          }
        }
      } else if (num == "0") {
          document.getElementById('num').innerHTML = 6
      } else {
        var num2 = document.getElementById('num').textContent
        op1 = num2 + 6
        document.getElementById('num').innerHTML = op1
      }
    }
  }
}

function seven() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (ifError != 0 || ifInfinity != 0) {
      if (operator_exist) {
        operator_equal = false
        if (op2.length < limit || operator_active) {
          if (operator_active) {
            op2 = "7"
            document.getElementById('num').innerHTML = 7
            operator_active = false
          } else {
            op2 = document.getElementById('num').textContent
            op2 += 7
            document.getElementById('num').innerHTML = op2
          }
        }
      } else if (num == "0") {
          document.getElementById('num').innerHTML = 7
      } else {
        var num2 = document.getElementById('num').textContent
        op1 = num2 + 7
        document.getElementById('num').innerHTML = op1
      }
    }
  }
}

function eight() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (ifError != 0 || ifInfinity != 0) {
      if (operator_exist) {
        operator_equal = false
        if (op2.length < limit || operator_active) {
          if (operator_active) {
            op2 = "8"
            document.getElementById('num').innerHTML = 8
            operator_active = false
          } else {
            op2 = document.getElementById('num').textContent
            op2 += 8
            document.getElementById('num').innerHTML = op2
          }
        }
      } else if (num == "0") {
          document.getElementById('num').innerHTML = 8
      } else {
        var num2 = document.getElementById('num').textContent
        op1 = num2 + 8
        document.getElementById('num').innerHTML = op1
      }
    }
  }
}

function nine() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  var ifInfinity = num.localeCompare("Infinity")
  if (num.length < limit || operator_exist) {
    if (ifError != 0 || ifInfinity != 0) {
      if (operator_exist) {
        operator_equal = false
        if (op2.length < limit || operator_active) {
          if (operator_active) {
            op2 = "9"
            document.getElementById('num').innerHTML = 9
            operator_active = false
          } else {
            op2 = document.getElementById('num').textContent
            op2 += 9
            document.getElementById('num').innerHTML = op2
          }
        }
      } else if (num == "0") {
          document.getElementById('num').innerHTML = 9
      } else {
        var num2 = document.getElementById('num').textContent
        op1 = num2 + 9
        document.getElementById('num').innerHTML = op1
      }
    }
  }
}

// others

function decimal() {
  if (equal_pressed) {
    ac()
  }
  document.getElementById('e').innerHTML = ""
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  if (!period_exist) {
    if (ifError != 0) {
      if (operator_active) {
        document.getElementById('num').innerHTML = "0."
        op2 = "0"
        operator_active = false
        operator_equal = false
      } else {
        var num2 = document.getElementById('num').textContent
        document.getElementById('num').innerHTML = num2 + "."
      }
    }
    limit++
  }
  period_exist = true
}

function negate() {
  var num = document.getElementById('num').textContent
  var ifError = num.localeCompare("Error")
  if (ifError != 0) {
    if (num.search("-") == -1) {
      if (num != "0") {
        var num2 = document.getElementById('num').textContent
        if (operator_exist) {
          if (equal_pressed) {
            op1 = "-" + num2
          } else {
            op2 = "-" + num2
          }
        } else {
          op1 = "-" + num2
        }
        document.getElementById('num').innerHTML = "-" + num2
      }
    } else {
      var num2 = num.substring(1,num.length)
      if (operator_exist) {
        if (equal_pressed) {
          op1 = num2
        } else {
          op2 = num2
        }
      } else {
        op1 = num2
      }
      document.getElementById('num').innerHTML = num2
    }
  }
  operator_equal = false
}

function del() {
  document.getElementById('num').innerHTML = 0
  op2 = "0"
  operator_equal = false
  document.getElementById('e').innerHTML = ""
  if (equal_pressed) {
    ac()
  }
}

function ac() {
  document.getElementById('num').innerHTML = 0
  period_exist = false
  operator_exist = false
  pressed_once = false
  operator_active = false
  equal_pressed = false
  operator_equal = true
  limit = 13
  document.getElementById('e').innerHTML = ""

  op1 = "A"
  op2 = "A"

  operator_type = ""
}

// operators

function add() {
  if (operator_exist) {
    if (!operator_active){
      equal()
    }
  } else {
    op1 = document.getElementById('num').textContent
  }
  operator_type = "add"
  operator_active = true
  operator_exist = true
  operator_equal = true
  equal_pressed = false
  period_exist = false
  limit = 13
}

function subtract() {
  if (operator_exist) {
    if (!operator_active){
      equal()
    }
  } else {
    op1 = document.getElementById('num').textContent
  }
  operator_type = "subtract"
  operator_active = true
  operator_exist = true
  operator_equal = true
  equal_pressed = false
  period_exist = false
  limit = 13
}

function multiply() {
  if (operator_exist) {
    if (!operator_active){
      equal()
    }
  } else {
    op1 = document.getElementById('num').textContent
  }
  operator_type = "multiply"
  operator_active = true
  operator_exist = true
  operator_equal = true
  equal_pressed = false
  period_exist = false
  limit = 13
}

function divide() {
  if (operator_exist) {
    if (!operator_active){
      equal()
    }
  } else {
    op1 = document.getElementById('num').textContent
  }
  operator_type = "divide"
  operator_active = true
  operator_exist = true
  operator_equal = true
  equal_pressed = false
  period_exist = false
  limit = 13
}

function equal() {

  if (operator_type == "add") {
    if (operator_exist){
      if (operator_equal) {
        op2 = op1
        operator_equal = false
      }
      var result = parseFloat(op1) + parseFloat(op2)
      console.log(parseFloat(op1))
      console.log(parseFloat(op2))
      if (result > 9999999999999 || result < -9999999999999) {
        result = result.toExponential()
      }
      var r = result.toString()
      var large
      if (r.search("e") != -1) {
        large = r.substring(r.search("e"))
        document.getElementById('e').innerHTML = large
      }
      if (r.search("-") == -1) {
        r = r.substring(0,14)
      } else {
        r = r.substring(0,15)
      }
      document.getElementById('num').innerHTML = r
      op1 = result
      console.log("result: " + op1)
    }
  } else if (operator_type == "subtract") {
    if (operator_exist){
      if (operator_equal) {
        op2 = op1
        operator_equal = false
      }
      var result = parseFloat(op1) - parseFloat(op2)
      console.log(parseFloat(op1))
      console.log(parseFloat(op2))
      if (result > 9999999999999 || result < -9999999999999) {
        result = result.toExponential()
      }
      var r = result.toString()
      var large
      if (r.search("e") != -1) {
        large = r.substring(r.search("e"))
        document.getElementById('e').innerHTML = large
      }
      if (r.search("-") == -1) {
        r = r.substring(0,14)
      } else {
        r = r.substring(0,15)
      }
      document.getElementById('num').innerHTML = r
      op1 = result
      console.log("result: " + op1)
    }
  } else if (operator_type == "multiply") {
    if (operator_exist){
      if (operator_equal) {
        op2 = op1
        operator_equal = false
      }
      var result = parseFloat(op1) * parseFloat(op2)
      console.log(parseFloat(op1))
      console.log(parseFloat(op2))
      if (result > 9999999999999 || result < -9999999999999) {
        result = result.toExponential()
      }
      var r = result.toString()
      var large
      if (r.search("e") != -1) {
        large = r.substring(r.search("e"))
        document.getElementById('e').innerHTML = large
      }
      if (r.search("-") == -1) {
        r = r.substring(0,14)
      } else {
        r = r.substring(0,15)
      }
      document.getElementById('num').innerHTML = r
      op1 = result
      console.log("result: " + op1)
    }
  } else if (operator_type == "divide") {
    if (operator_exist){
      if (operator_equal) {
        op2 = op1
        operator_equal = false
      }
      if (op2 == 0) {
        document.getElementById('num').innerHTML = "Error"
      } else {
        var result = parseFloat(op1) / parseFloat(op2)
        console.log(parseFloat(op1))
        console.log(parseFloat(op2))
        if (result > 9999999999999 || result < -9999999999999) {
          result = result.toExponential()
        }
        var r = result.toString()
        var large
        if (r.search("e") != -1) {
          large = r.substring(r.search("e"))
          document.getElementById('e').innerHTML = large
        }
        if (r.search("-") == -1) {
          r = r.substring(0,14)
        } else {
          r = r.substring(0,15)
        }
        document.getElementById('num').innerHTML = r
        op1 = result
        console.log("result: " + op1)
      }
    }
  }
  equal_pressed = true
  operator_active = true
  limit = 13
}

// extra functions