package br.com.cwi.demo.Events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.cwi.demo.Classes.Car;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarEvents {

    private Car model;
    private Date when;
}