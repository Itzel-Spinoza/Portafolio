using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Jugador : MonoBehaviour
{
   public float fuerzaSalto;
   public float velocidadMovimiento;
    private Rigidbody2D rigidbody2D; 
    private Animator animator; 

    // Start is called before the first frame update
    void Start()
    {
        animator = GetComponent<Animator>();
        rigidbody2D = GetComponent<Rigidbody2D>(); 
    }

    // Update is called once per frame
    void Update()
    {
        float movimientoHorizontal = Input.GetAxis("Horizontal");

        // Actualizar animación de caminar
        animator.SetBool("Caminando", movimientoHorizontal != 0f);

        // Mover horizontalmente
        rigidbody2D.velocity = new Vector2(movimientoHorizontal * velocidadMovimiento, rigidbody2D.velocity.y);
        
        if (Input.GetKeyDown(KeyCode.Space))
        {
            animator.SetBool("EstaSaltando", true);
            rigidbody2D.AddForce(new Vector2(0, fuerzaSalto));
        }

    
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.gameObject.tag == "Suelo")
        {
            animator.SetBool("EstaSaltando", false);
        }
    }
}
