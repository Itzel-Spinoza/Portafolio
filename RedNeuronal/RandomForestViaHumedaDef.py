import tkinter as tk
from tkinter import ttk
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error
from sklearn.preprocessing import MinMaxScaler

# Funciones para el modelo
def normalize_data(df):
    scaler = MinMaxScaler()
    df_normalized = pd.DataFrame(scaler.fit_transform(df), columns=df.columns)
    return df_normalized, scaler

def predict_performance(model, input_data):
    input_data_normalized = scaler.transform(input_data)
    input_data_normalized = pd.DataFrame(input_data_normalized, columns=input_data.columns)
    prediction = model.predict(input_data_normalized)
    return prediction

def denormalize_single_value(value, scaler):
    denormalized_value = scaler.inverse_transform(np.array([[value, 0, 0]]))
    return denormalized_value[0, 0]


# Funciones para la interfaz gráfica
def on_predict():
    # Obtener valores de entrada del usuario desde la interfaz gráfica
    tiempo_sedimentacion = float(entry_sedimentacion.get())
    temperatura_secado = float(entry_temperatura.get())
    tiempo_secado = float(entry_tiempo.get())

    # Crear un nuevo DataFrame con los valores de entrada del usuario
    new_data = {
        'tiempo_sedimentacion': [tiempo_sedimentacion],
        'temperatura_secado': [temperatura_secado],
        'tiempo_secado': [tiempo_secado],
    }

    df_user_input = pd.DataFrame(new_data)
    # Realizar la predicción
    predicted_normalized = predict_performance(model, df_user_input)

    # Desnormalizar el valor predicho
    predicted_denormalized = denormalize_single_value(predicted_normalized[0], scaler)
    
    predicted_label.set(f"Rendimiento: {predicted_normalized:.4f}")
# Crear la interfaz gráfica
root = tk.Tk()
root.title("Predicción de Rendimiento para vía húmeda")

# Variables para la interfaz gráfica
accuracy_label = tk.StringVar()
predicted_label = tk.StringVar()

# Crear etiquetas y campos de entrada para cada característica
labels = ["tiempo_sedimentacion", "temperatura_secado", "tiempo_secado"]

for i, label_text in enumerate(labels):
    label = ttk.Label(root, text=label_text)
    label.grid(row=i, column=0, padx=5, pady=5, sticky="e")
    entry = ttk.Entry(root)
    entry.grid(row=i, column=1, padx=5, pady=5)
    if i == 0:
        entry_sedimentacion = entry
    elif i == 1:
        entry_temperatura = entry
    elif i == 2:
        entry_tiempo = entry

# Crear botón de predicción
predict_button = ttk.Button(root, text="Predecir", command=on_predict)
predict_button.grid(row=len(labels), column=0, columnspan=2, pady=10)

# Etiqueta para mostrar la predicción
result_label = ttk.Label(root, textvariable=predicted_label)
result_label.grid(row=len(labels) + 1, column=0, columnspan=2, pady=10)

# Etiqueta para mostrar el porcentaje de aciertos en la prueba
accuracy_result_label = ttk.Label(root, textvariable=accuracy_label)
accuracy_result_label.grid(row=len(labels) + 2, column=0, columnspan=2, pady=10)


# Cargar el dataset desde un archivo Excel
file_path = 'C:\\Users\\itzel\\Documents\\7MO SEMESTRE\\SERVICIO SOCIAL\\via_humeda.csv'
df = pd.read_csv(file_path)

# Dividir el conjunto de datos en características (X) y etiquetas (y)
X = df.drop("rendimiento", axis=1)
y = df["rendimiento"]

# Normalizar los datos entre 0 y 1 usando la función
X_normalized, scaler = normalize_data(X)

# Dividir el conjunto de datos normalizado en conjuntos de entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(X_normalized, y, test_size=0.2, random_state=42)

# Construir el modelo de Random Forest
model = RandomForestRegressor(n_estimators=100, random_state=42)

# Entrenar el modelo
model.fit(X_train, y_train)

# Evaluar el modelo en el conjunto de prueba
y_pred = model.predict(X_test)

# Calcular el porcentaje de aciertos
mse = mean_squared_error(y_test, y_pred)
accuracy = 100 * (1 - mse)
accuracy_label.set(f"Porcentaje de aciertos en la prueba: {accuracy:.2f}%")

# Ejecutar la interfaz gráfica
root.mainloop()
