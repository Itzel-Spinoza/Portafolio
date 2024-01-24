using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MenuInicio : MonoBehaviour
{

    //public QuestionPanel questionPanel2;
    public void Jugar()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex + 1);
    }

    public void Jugar2()
    {
        //questionPanel2.ResetScores(); // Restablecer puntajes
        SceneManager.LoadScene("MenuInicial");

    }

    public void Salir()
    {
        Debug.Log("Salir...");
        Application.Quit();
    }
}
