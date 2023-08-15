import re


# Funciones
def get_parenthesis(sentence: str) -> str:
    """
    ***
    * sentence : str
    ***
    De una sentencia, si es que posee parentesis, extrae la sentencia dentro de los
    parentesis y devuelve lo extraido, en caso de que no haya parentesis,
    devuelve la sentencia original.
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
                if parenthesis_count == 0:
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

    operation = re.search(rf"\-?({ENTERO})\{operator}\-?({ENTERO})", sentence).group(0)
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
    percentage = int(percentage) if optional else 20

    if percentage > 100:
        return ""  # Porcentaje dado no valido

    # Calculo aplicar cupon
    result = int(value * (percentage / 100))
    if optional:
        return sentence.replace(f"CUPON({value},{percentage})", str(result))

    return sentence.replace(f"CUPON({value})", str(result))


def calculator(sentence: str, ans: int) -> int:
    """
    ***
    * sentence : str
    * ans : int
    ***
    Función principal del programa, recibe una sentencia y el resultado
    del calculo anterior, para luego calcular la solucion de la sentencia
    dada y entregarla.
    """

    # Resolver parentesis primero
    problem: str = sentence
    while "(" in problem:
        subsentence: str = get_parenthesis(problem)
        if subsentence == problem:
            break

        result: int = calculator(subsentence, ans)
        if result == -1:
            return result  # Abortar en caso de error

        problem = problem.replace(f"({subsentence})", str(result))
        # Caso de que el reemplazo sea el resultado final
        if re.fullmatch("[0-9]+", problem) is not None:
            return result

    # Chequeo de sintaxis valida
    check = re.match(SENTENCIA, problem)
    if check is None:
        return -1

    # Formatear problema
    problem = problem.replace("ANS", f"{ans}")  # Reemplazar ANS por previo resultado
    problem = problem.replace(" ", "")  # Eliminar espacios

    # Aplicar cupones
    while "CUPON" in problem:
        problem = apply_cupon(problem)
        if problem == "":
            return -1

    # Calculo de operaciones segun prioridad y luego de izquierda a derecha
    operators = ["*", "//", "+", "-"]
    while re.fullmatch(ENTERO, problem) is None:
        # Cambio de prioridad
        if operators[0] not in problem and operators[1] not in problem:
            operators.pop(0)
            operators.pop(0)

        # Encontrar primer operador de izquierda a derecha en la prioridad actual
        operator_index1 = problem.find(operators[0])
        operator_index2 = problem.find(operators[1])
        if (
            operator_index1 < operator_index2 and operator_index1 != -1
        ) or operator_index2 == -1:
            operator = operators[0]
        else:
            operator = operators[1]

        (left_num, right_num) = get_numbers(problem, operator)

        match operator:
            case "*":
                result = left_num * right_num
            case "//":
                if right_num == 0:
                    return -1  # 0 division error

                result = left_num // right_num
            case "+":
                result = left_num + right_num
            case "-":
                result = left_num - right_num
                if result < 0:
                    result = 0
            case _:
                result = -1  # Unknown operator error

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
sentence_stack = []
result_stack = []

for line in PROBLEMAS_FILE:
    # Guardar resultados
    if line == "\n":
        for index in range(len(sentence_stack)):
            sentence = sentence_stack[index]
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
        sentence_stack.clear()
        result_stack.clear()
        continue

    sentence = line.replace("\n", "")
    sentence_stack.append(sentence)
    # Chequeo de paridad de los parentesis
    if sentence.count("(") != sentence.count(")"):
        result_stack.append("Error")
        continue

    # Añadir resultados
    ans = calculator(sentence, ans)
    if ans == -1:  # Detección de errores durante el calculo
        result_stack.append("Error")
        continue

    result_stack.append(ans)

# Guardar resultados de ultimo problema
if len(sentence_stack) > 0:
    for index in range(len(sentence_stack)):
        sentence = sentence_stack[index].replace("\n", "")
        # Chequear si a habido algún error
        if "Error" in result_stack:  # Errores Detectados
            if result_stack[index] == "Error":
                DESARROLLOS_FILE.write(f"{sentence} = {result_stack[index]}\n")
            else:
                DESARROLLOS_FILE.write(f"{sentence} = Sin resolver\n")
        else:  # Procedimiento Normal
            DESARROLLOS_FILE.write(f"{sentence} = {result_stack[index]}\n")

PROBLEMAS_FILE.close()
DESARROLLOS_FILE.close()
