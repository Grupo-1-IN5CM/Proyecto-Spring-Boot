package com.agenciaviajes.webapp.agenciaViajes.utils;


import org.springframework.stereotype.Component;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;

@Component
public class Alerta {


    public void mostrarAlertInfo(int code) {
        if (code == 123) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("¡Registro Completado!");
            alert.setHeaderText("¡Registro Completado!");
            alert.setContentText("¡La busqueda fue un éxito.");
            alert.showAndWait();
        } else if (code == 789) { 
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("¡Edición Exitosa!");
            alert.setHeaderText("¡Edición Exitosa!");
            alert.setContentText("¡Los cambios fueron guardados con éxito!");
            alert.showAndWait();
        } else if (code == 341) { 
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Campos Faltantes");
            alert.setHeaderText("¡Falta completar algunos campos!");
            alert.setContentText("Asegúrate de llenar todos los campos importantes antes de continuar.");
            alert.showAndWait();
        } else if (code == 456) { 
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No se pueden eliminar los datos");
            alert.setHeaderText("No se pueden eliminar los datos");
            alert.setContentText("Estos datos están relacionados con otra información y no se pueden borrar.");
            alert.showAndWait();
        }
    }
    

    public void alertaVerificacion(int code) {
        if (code == 1578) {
            // Validar Calificación En Rango
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Calificación Fuera de Rango");
            alert.setHeaderText("La calificación ingresada no es válida");
            alert.setContentText("La calificación debe estar entre 1 y 5.");
            alert.showAndWait();
        } else if (code == 2436) {
            // Validar Comentario No Vacío
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Comentario Vacío");
            alert.setHeaderText("El comentario no puede estar vacío");
            alert.setContentText("Debe ingresar un comentario antes de continuar.");
            alert.showAndWait();
        } else if (code == 3954) {
            // Validar Fechas Consistentes
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Fechas Inconsistentes");
            alert.setHeaderText("Las fechas ingresadas no son válidas");
            alert.setContentText("La fecha de inicio no puede ser posterior a la fecha final.");
            alert.showAndWait();
        } else if (code == 4791) {
            // Validar si el Vehículo está Disponible
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Vehículo No Disponible");
            alert.setHeaderText("El vehículo seleccionado no está disponible");
            alert.setContentText("Por favor, seleccione un vehículo que esté disponible.");
            alert.showAndWait();
        }
    }
}    
