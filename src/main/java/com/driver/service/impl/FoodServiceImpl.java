package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = new FoodEntity();

        foodEntity.setFoodId(food.getFoodId());
        foodEntity.setFoodName(food.getFoodName());
        foodEntity.setFoodCategory(food.getFoodCategory());
        foodEntity.setFoodPrice(food.getFoodPrice());

        FoodEntity savedFoodEntity = foodRepository.save(foodEntity);

        FoodDto response = new FoodDto();

        response.setId(savedFoodEntity.getId());
        response.setFoodId(savedFoodEntity.getFoodId());
        response.setFoodName(savedFoodEntity.getFoodName());
        response.setFoodPrice(savedFoodEntity.getFoodPrice());
        response.setFoodCategory(savedFoodEntity.getFoodCategory());

        return response;
    }


    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        try{
            FoodEntity foodEntity = foodRepository.findByFoodId(foodId);

            if(foodEntity == null){
                return new FoodDto();
            }
            FoodDto foodDto = new FoodDto();

            foodDto.setId(foodEntity.getId());
            foodDto.setFoodId(foodEntity.getFoodId());
            foodDto.setFoodCategory(foodEntity.getFoodCategory());
            foodDto.setFoodName(foodEntity.getFoodName());
            foodDto.setFoodPrice(foodEntity.getFoodPrice());

            return foodDto;
        }
        catch (Exception e){
            throw new Exception("Food Not Found");
        }
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        try{
            FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
            if(foodEntity==null){
                return new FoodDto();
            }

            foodEntity.setFoodName(foodDetails.getFoodName());
            foodEntity.setFoodPrice(foodDetails.getFoodPrice());
            foodEntity.setFoodCategory(foodDetails.getFoodCategory());

            FoodEntity updatedFood = foodRepository.save(foodEntity);

            FoodDto response = new FoodDto();

            response.setId(updatedFood.getId());
            response.setFoodId(updatedFood.getFoodId());
            response.setFoodName(updatedFood.getFoodName());
            response.setFoodPrice(updatedFood.getFoodPrice());
            response.setFoodCategory(updatedFood.getFoodCategory());

            return response;
        }catch (Exception e){
            throw  new Exception("Food not found");
        }

    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        try{
            FoodEntity response = foodRepository.findByFoodId(id);
            foodRepository.delete(response);
        }
        catch (Exception e){
            throw new Exception("Food Not Found");
        }
    }

    @Override
    public List<FoodDto> getFoods() {
        Iterable<FoodEntity> responses = foodRepository.findAll();
        List<FoodDto> ans = new ArrayList<>();

        for (FoodEntity foodEntity: responses) {
            FoodDto foodDto = new FoodDto();

            foodDto.setId(foodEntity.getId());
            foodDto.setFoodId(foodEntity.getFoodId());
            foodDto.setFoodName(foodEntity.getFoodName());
            foodDto.setFoodPrice(foodEntity.getFoodPrice());
            foodDto.setFoodCategory(foodEntity.getFoodCategory());

            ans.add(foodDto);
        }

        return ans;
    }
}