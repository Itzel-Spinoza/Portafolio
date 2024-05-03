package com.example.libreriaelbuho.controlador

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.libreriaelbuho.modelo.MIniciarSesion
import com.google.firebase.firestore.FirebaseFirestore
import androidx.navigation.NavController.*



class IniciarSesion {

        private val firestore = FirebaseFirestore.getInstance()

        fun iniciarSesion(navController: NavController, correo: String, contrasena: String) {
            val modelo = MIniciarSesion(null, correo, contrasena, "")

            modelo.verificar(navController) { resultado, tipoUsuario ->
                if (resultado) {

                    val destino = if (tipoUsuario == "Cliente") {
                        modelo.verificarNombreUsuario(navController,correo) { resultado, nombre ->
                            if (resultado) {
                                navController.navigate("inicioScreen/$correo/$nombre")
                            }
                        }
                    } else {
                        modelo.verificarNombreUsuario(navController,correo) { resultado, nombre ->
                            if (resultado) {
                                navController.navigate("ventanaInicioAdmin/$correo/$nombre")
                            }
                        }

                    }

                    Toast.makeText(navController.context, "Usuario autentificado.", Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(
                        navController.context,
                        "Error. Usuario no encontrado o incorrecto.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        fun verificarTipo(navController: NavController, correo: String, contrasena: String) {
            val modelo = MIniciarSesion(null, correo, contrasena, "")

            modelo.verificar(navController) { resultado, tipoUsuario ->
                if (resultado) {
                    val destino = if (tipoUsuario == "Administrador") {
                        modelo.verificarNombreUsuario(navController,correo) { resultado, nombre ->
                            if (resultado) {
                                navController.navigate("ventanaInicioAdmin/$correo/$nombre")
                            }
                        }
                    } else {
                        modelo.verificarNombreUsuario(navController,correo) { resultado, nombre ->
                            if (resultado) {
                                navController.navigate("inicioScreen/$correo/$nombre")
                            }
                        }
                    }
                    Toast.makeText(navController.context, "Usuario autentificado.", Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(
                        navController.context,
                        "Error. Usuario no encontrado o incorrecto.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }







}

