package com.example.weight
data class Planet(
    val name: String,
    val mass: Double, // in kg
    val gravity: Double, // in m/s^2
    val resources: List<String>
)
