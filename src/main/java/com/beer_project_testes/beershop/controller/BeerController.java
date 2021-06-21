package com.beer_project_testes.beershop.controller;

import com.beer_project_testes.beershop.dto.BeerDTO;
import com.beer_project_testes.beershop.dto.QuantityDTO;
import com.beer_project_testes.beershop.exception.BeerAlreadyRegisteredException;
import com.beer_project_testes.beershop.exception.BeerNotFoundException;
import com.beer_project_testes.beershop.exception.BeerStockExceededException;
import com.beer_project_testes.beershop.service.BeerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Beer Stock API REST")
@CrossOrigin(origins = "*")
public class BeerController implements BeerControllerDocs {

    private final BeerService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria o objeto cerveja")
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        return beerService.createBeer(beerDTO);
    }

    @GetMapping("/{name}")
    @ApiOperation(value = "Retorna uma única cerveja quando existente")
    public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de cervejas")
    public List<BeerDTO> listBeers() {
        return beerService.listAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta uma cerveja")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws BeerNotFoundException {
        beerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    @ApiOperation(value = "Incrementa a quantidade da cerveja")
    public BeerDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockExceededException {
        return beerService.increment(id, quantityDTO.getQuantity());
    }

    @PatchMapping("/{id}/decrement")
    @ApiOperation(value = "Decrementa a quantidade da cerveja")
    public BeerDTO decrement(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockExceededException {
        return beerService.decrement(id, quantityDTO.getQuantity());
    }
}
