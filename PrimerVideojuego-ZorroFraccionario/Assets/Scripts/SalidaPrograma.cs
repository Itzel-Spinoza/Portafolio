using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SalidaPrograma : MonoBehaviour
{
    //se establecen las variables
    public GameObject endPanel;
    public GameObject advertenciaPanel;

    //Es el metodo que sirve para cuando el jugardor colicione contra el girasol
    private void OnTriggerEnter2D(Collider2D collision)
    {
        //si coliciona pasa el if 
        if (collision.CompareTag("Player"))
        {
            GameObject[] apples = GameObject.FindGameObjectsWithTag("Apple");

            //si la longitud es 0, se mostrara el panel del fin de juego y si no es 0 mostrara un mensaje que diga que 
            //no se han contestado todas las preguntas
            if (apples.Length == 0)
            {
                // Mostrar panel de fin del juego
                endPanel.SetActive(true);
            }
            else
            {
                // Mostrar mensaje de que no se ha contestado todo y volver al juego
                advertenciaPanel.SetActive(true);
                //este metodo llama el metodo que se rara el panel despues de 2 segundos
                Invoke("CloseAdvertenciaPanel", 2f);
            }
        }
    }
    //Sirve para cerrar el panel
    private void CloseAdvertenciaPanel()
    {
        // Cierra el panel de advertencia
        advertenciaPanel.SetActive(false);
    }
}
