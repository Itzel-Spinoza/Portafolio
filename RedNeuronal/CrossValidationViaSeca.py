import tkinter as tk
from tkinter import ttk
import numpy as np
import pandas as pd
from sklearn.model_selection import KFold
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

# Funciones para la interfaz gráfica
def on_predict():
    # Obtener valores de entrada del usuario desde la interfaz gráfica
    Concentracion_metabisulfito_1 = float(entry_concentracion.get())
    temperatura_contacto = float(entry_temperatura.get())
    tiempo_contacto = float(entry_tiempo.get())
    Concentracion_metabisulfito_2 = float(entry_concentracion_2.get())
    tiempo_contacto_2 = float(entry_tiempo_2.get())
    temperatura_secado = float(entry_temperatura_secado.get())
    tiempo_secado = float(entry_tiempo_secado.get())

    # Crear un nuevo DataFrame con los valores de entrada del usuario
    new_data = {
        'Concentracion_metabisulfito_1': [Concentracion_metabisulfito_1],
        'temperatura_contacto': [temperatura_contacto],
        'tiempo_contacto': [tiempo_contacto],
        'Concentracion_metabisulfito_2': [Concentracion_metabisulfito_2],
        'tiempo_contacto_2': [tiempo_contacto_2],
        'temperatura_secado': [temperatura_secado],
        'tiempo_secado': [tiempo_secado],
    }

    df_user_input = pd.DataFrame(new_data)
    # Realizar la predicción
    predicted_normalized = predict_performance(model, df_user_input)
    predicted_label.set(f"Rendimiento: {predicted_normalized[0][0]:.4f}")

# Función para entrenar el modelo y evaluar la precisión usando validación cruzada
def train_and_evaluate(X, y, folds=5):
    kf = KFold(n_splits=folds, shuffle=True, random_state=42)

    accuracies = []
    for train_index, test_index in kf.split(X):
        # Dividir el conjunto de entrenamiento y prueba
        X_train, X_test = X.iloc[train_index], X.iloc[test_index]
        y_train, y_test = y.iloc[train_index], y.iloc[test_index]

        model = build_model(X_train.shape[1])
        model.fit(X_train, y_train, epochs=300, batch_size=8, verbose=0)
        y_pred = model.predict(X_test)

        mse = np.mean(np.square(y_test.values.ravel() - y_pred.ravel()))
        accuracy = 100 * (1 - mse)
        accuracies.append(accuracy)

    average_accuracy = np.mean(accuracies)
    return average_accuracy

# Crear la interfaz gráfica
root = tk.Tk()
root.title("Predicción de Rendimiento para vía seca")

# Variables para la interfaz gráfica
accuracy_label = tk.StringVar()

predicted_label = tk.StringVar()

# Crear etiquetas y campos de entrada para cada característica
labels = ["Concentracion_metabisulfito_1", "temperatura_contacto", "tiempo_contacto",
          "Concentracion_metabisulfito_2", "tiempo_contacto_2", "temperatura_secado", "tiempo_secado"]

for i, label_text in enumerate(labels):
    label = ttk.Label(root, text=label_text)
    label.grid(row=i, column=0, padx=5, pady=5, sticky="e")
    entry = ttk.Entry(root)
    entry.grid(row=i, column=1, padx=5, pady=5)
    if i == 0:
        entry_concentracion = entry
    elif i == 1:
        entry_temperatura = entry
    elif i == 2:
        entry_tiempo = entry
    elif i == 3:
        entry_concentracion_2 = entry
    elif i == 4:
        entry_tiempo_2 = entry
    elif i == 5:
        entry_temperatura_secado = entry
    elif i == 6:
        entry_tiempo_secado = entry

# Crear botón de predicción
predict_button = ttk.Button(root, text="Predecir", command=on_predict)
predict_button.grid(row=len(labels), column=0, columnspan=2, pady=10)

# Etiqueta para mostrar la predicción
result_label = ttk.Label(root, textvariable=predicted_label)
result_label.grid(row=len(labels) + 1, column=0, columnspan=2, pady=10)

# Etiqueta para mostrar el porcentaje de aciertos en la prueba
accuracy_result_label = ttk.Label(root, textvariable=accuracy_label)
accuracy_result_label.grid(row=len(labels) + 2, column=0, columnspan=2, pady=10)


# Funciones para el modelo
def normalize_data(df):
    scaler = MinMaxScaler()
    df_normalized = pd.DataFrame(scaler.fit_transform(df), columns=df.columns)
    return df_normalized, scaler

def denormalize(zi, df):
    df_denormalized = zi(max(df)-min(df))+ min(df)
    return df_denormalized

def predict_performance(model, input_data):
    input_data_normalized = scaler.transform(input_data)
    input_data_normalized = pd.DataFrame(input_data_normalized, columns=input_data.columns)
    prediction = model.predict(input_data_normalized)
    return prediction

def build_model(input_dim):
    model = Sequential()
    model.add(Dense(8, input_dim=input_dim, activation='relu'))
    model.add(Dense(4, activation='relu'))
    model.add(Dense(1, activation='linear'))
    model.compile(loss='mean_squared_error', optimizer='adam', metrics=['mse'])
    return model

# Cargar el dataset desde un archivo Excel
file_path = 'C:\\Users\\itzel\\Documents\\7MO SEMESTRE\\SERVICIO SOCIAL\\via_seca.csv'
df = pd.read_csv(file_path)

# Dividir el conjunto de datos en características (X) y etiquetas (y)
X = df.drop("rendimiento", axis=1)
y = df["rendimiento"]

# Normalizar los datos entre 0 y 1 usando la función
X_normalized, scaler = normalize_data(X)

# Evaluar el modelo usando validación cruzada
average_accuracy = train_and_evaluate(X_normalized, y, folds=5)
accuracy_label.set(f"Porcentaje de aciertos en la prueba (validación cruzada): {average_accuracy:.2f}%")

# Construir el modelo de red neuronal
model = build_model(X_normalized.shape[1])

# Entrenar el modelo
model.fit(X_normalized, y, epochs=200, batch_size=8, verbose=1)

# Ejecutar la interfaz gráfica
root.mainloop()
