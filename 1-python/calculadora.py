import re


# Funciones
def sentence_check(sentence: str) -> bool:
    """
    ***
    * sentence : str
    ***
    Chequea si la sentencia entregada corresponde a una sentencia valida
    de la calculadora, devuelve True si es el caso y False si no.
    """

    # Descartar errores comunes
    if sentence.count("(") != sentence.count(")"):  # Parentesis sin pares
        return False
    elif re.search(r"\/\/ *0", sentence) is not None:  #! División por 0
        return False

    # Ver si la sentencia es sintacticamente correcta
    check = re.match(SENTENCIA, sentence)
    if check is not None:
        return True
    elif "(" not in sentence:
        return False

    # Determinar si sentencias en parentesis son correctas
    parenthesis = get_parenthesis(sentence)
    position = 0
    while parenthesis != sentence:
        if not sentence_check(parenthesis):
            return False
        position += 1
        parenthesis = get_parenthesis(sentence, position)

    return True


def get_parenthesis(sentence: str, position: int = 0) -> str:
    """
    ***
    * sentence : str
    * position : int
    ***
    De una sentencia, si es que posee parentesis, extrae la sentencia dentro de los
    segun la posicion dada [(0)+(1)-(2)] y devuelve lo extraido, en caso de que no hayan parentesis
    o no haya un parentesis en la posicion dada, devuelve la sentencia original.
    """

    start = 0
    end = 0
    parenthesis_count = 0
    cupon_mode = False
    for index in range(len(sentence)):
        char: str = sentence[index]

        match char:
            case "C":
                cupon_mode = True
            case "(":
                if cupon_mode:
                    continue

                if parenthesis_count == 0:
                    start = index + 1
                parenthesis_count += 1
            case ")":
                if cupon_mode:
                    cupon_mode = False
                    continue

                parenthesis_count -= 1
                if position > 0:
                    position -= 1
                elif parenthesis_count == 0:
                    end = index
                    return sentence[start:end]

    return sentence


def get_numbers(sentence: str, operator: str) -> (int, int):
    """
    ***
    * sentence : str
    * operator : str
    ***
    De una sentencia correcta, busca el primer operador (el entregado) que se encuentra en
    la sentencia, y extrae los números que son parte de la operación en una tupla
    de dos numeroa (izquierda, derecha).
    """

    operation = re.search(rf"({ENTERO})\{operator}({ENTERO})", sentence).group(0)
    nums = operation.split(operator)

    return (int(nums[0]), int(nums[1]))


def apply_cupon(sentence: str) -> str:
    """
    ***
    * sentence : str
    ***
    En una sentencia valida, busca la funcion CUPON, saca sus argumentos
    y calcula el resultado, para luego reemplazar esa funcion por el resultado
    en la sentencia dada.
    """

    index: int = sentence.find("CUPON") + 6  # Acceder a parametros
    value = ""
    percentage = ""
    char: str = sentence[index]

    # Extraer valor
    while not char in ",)":
        value += char
        index += 1
        char = sentence[index]

    # Extraer porcentaje
    if char == ",":
        index += 1
        char = sentence[index]
        while not char in ")":
            percentage += char
            index += 1
            char = sentence[index]

    value = int(value)
    optional: bool = percentage != ""
    percentage = (
        int(percentage) if optional else 20
    )  # TODO percentage <= 100 sino error

    # Calculo aplicar cupon
    result = int(value * (percentage / 100))
    if optional:
        return sentence.replace(f"CUPON({value},{percentage})", str(result))

    return sentence.replace(f"CUPON({value})", str(result))


def calculator(sentence: str) -> int:
    """
    ***
    * sentence : str
    ***
    Función principal del programa, recibe una sentencia correcta y calcula
    su resultado para luego devolverlo.
    """

    # Resolver parentesis primero
    problem: str = sentence
    while "(" in problem:
        subsentence: str = get_parenthesis(problem)
        result: int = calculator(subsentence)
        problem = problem.replace(f"({subsentence})", str(result))

    # Calculo de operaciones segun prioridad y luego de izquierda a
    OPERATORS = ["*", "//", "-", "+"]
    for operator in OPERATORS:
        # TODO sumas y restas de izquierda a derecha
        while operator in problem:
            (left_num, right_num) = get_numbers(problem, operator)

            match operator:
                case "*":
                    result = left_num * right_num
                case "//":
                    result = (
                        left_num // right_num
                    )  # TODO Division por cero debe tirar error
                case "-":
                    result = left_num - right_num
                    if result < 0:
                        result = 0
                case "+":
                    result = left_num + right_num
                case _:
                    result = -1  # Error

            problem = problem.replace(f"{left_num}{operator}{right_num}", str(result))

    return int(problem)


# Regex Expresions
DIGITO = r"[1-9]"
DIGITO_O_ZERO = rf"{DIGITO}|0"
ENTERO = rf"{DIGITO}({DIGITO_O_ZERO})*|0"
ESPACIO = r" "
CLAVE = rf"ANS|CUPON\({ESPACIO}*({ENTERO}|ANS)({ESPACIO}*,{ESPACIO}({ENTERO}|ANS){ESPACIO}*)?\)"
OPERADOR = rf"{ESPACIO}*([\+\-\*]|\/\/){ESPACIO}*"
OPERACION = rf"({CLAVE}|{ENTERO}){OPERADOR}({CLAVE}|{ENTERO})"
SENTENCIA = rf"{OPERACION}({OPERADOR}({ENTERO}|{CLAVE}))*"

# Main Loop
PROBLEMAS_FILE = open("problemas.txt")
DESARROLLOS_FILE = open("desarrollos.txt", "w")
ans = 0
line_stack = []
result_stack = []

for line in PROBLEMAS_FILE:
    # Guardar resultados
    if line == "\n":  # TODO Final de archivo
        for index in range(len(line_stack)):
            sentence = line_stack[index].replace("\n", "")
            # Chequear si a habido algún error
            if "Error" in result_stack:  # Errores Detectados
                if result_stack[index] == "Error":
                    DESARROLLOS_FILE.write(f"{sentence} = {result_stack[index]}\n")
                else:
                    DESARROLLOS_FILE.write(f"{sentence} = Sin resolver\n")
            else:  # Procedimiento Normal
                DESARROLLOS_FILE.write(f"{sentence} = {result_stack[index]}\n")
        DESARROLLOS_FILE.write("\n")

        # Resetear Calculadora
        ans = 0
        line_stack.clear()
        result_stack.clear()
        continue

    line_stack.append(line)
    # Chequear valides de las sentencias
    if not sentence_check(line):
        result_stack.append("Error")
        continue

    # Formatear sentencia de una forma mas simple
    sentence = line.replace("ANS", f"{ans}")  # Reemplazar ANS por previo resultado
    sentence = sentence.replace(" ", "")  # Quitar espacios

    while "CUPON" in sentence:
        sentence = apply_cupon(sentence)

    # Añadir resultados
    ans = calculator(sentence)
    result_stack.append(ans)

PROBLEMAS_FILE.close()
DESARROLLOS_FILE.close()
