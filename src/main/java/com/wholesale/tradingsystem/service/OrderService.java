package com.wholesale.tradingsystem.service;

import com.wholesale.tradingsystem.dto.OrderDTO;
import com.wholesale.tradingsystem.model.entity.Customer;
import com.wholesale.tradingsystem.model.entity.Order;
import com.wholesale.tradingsystem.model.entity.OrderDetail;
import com.wholesale.tradingsystem.model.entity.Product;
import com.wholesale.tradingsystem.repository.CustomerRepository;
import com.wholesale.tradingsystem.repository.OrderRepository;
import com.wholesale.tradingsystem.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, 
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Integer orderNumber) {
        return orderRepository.findById(orderNumber)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EmptyResultDataAccessException("Order not found with number: " + orderNumber, 1));
    }

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    @Transactional
    public OrderDTO updateOrder(Integer orderNumber, OrderDTO orderDTO) {
        if (!orderRepository.existsById(orderNumber)) {
            throw new EntityNotFoundException("Order not found with number: " + orderNumber);
        }

        Order order = convertToEntity(orderDTO);
        order.setOrderNumber(orderNumber); // ensure we update the existing entity
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }

    @Transactional
    public void deleteOrder(Integer orderNumber) {
        if (!orderRepository.existsById(orderNumber)) {
            throw new EmptyResultDataAccessException("Order not found with number: " + orderNumber, 1);
        }
        orderRepository.deleteById(orderNumber);
    }

    public List<OrderDTO> getOrdersByCustomer(Integer customerNumber) {
        return orderRepository.findByCustomer_CustomerNumber(customerNumber)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderNumber(order.getOrderNumber());
        dto.setOrderDate(order.getOrderDate());
        dto.setRequiredDate(order.getRequiredDate());
        dto.setShippedDate(order.getShippedDate());
        dto.setStatus(order.getStatus());
        dto.setComments(order.getComments());
        dto.setCustomerNumber(order.getCustomer().getCustomerNumber());

        // Convert order details
        List<OrderDTO.OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        if (order.getOrderDetails() != null) {
            orderDetailDTOs = order.getOrderDetails().stream()
                    .map(detail -> {
                        OrderDTO.OrderDetailDTO detailDTO = new OrderDTO.OrderDetailDTO();
                        detailDTO.setProductCode(detail.getProduct().getProductCode());
                        detailDTO.setQuantityOrdered(detail.getQuantityOrdered());
                        detailDTO.setPriceEach(detail.getPriceEach().doubleValue());
                        detailDTO.setOrderLineNumber(detail.getOrderLineNumber());
                        return detailDTO;
                    })
                    .collect(Collectors.toList());
        }
        dto.setOrderDetails(orderDetailDTOs);

        return dto;
    }

    private Order convertToEntity(OrderDTO dto) {
        Order entity = new Order();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setOrderDate(dto.getOrderDate());
        entity.setRequiredDate(dto.getRequiredDate());
        entity.setShippedDate(dto.getShippedDate());
        entity.setStatus(dto.getStatus());
        entity.setComments(dto.getComments());

        // Set customer
        Customer customer = customerRepository.findById(dto.getCustomerNumber())
                .orElseThrow(() -> new EmptyResultDataAccessException("Customer not found with number: " + dto.getCustomerNumber(), 1));
        entity.setCustomer(customer);

        // Set order details if present
        if (dto.getOrderDetails() != null) {
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (OrderDTO.OrderDetailDTO detailDTO : dto.getOrderDetails()) {
                OrderDetail detail = new OrderDetail();
                detail.setOrder(entity);

                // Set product
                Product product = productRepository.findById(detailDTO.getProductCode())
                        .orElseThrow(() -> new EmptyResultDataAccessException("Product not found with code: " + detailDTO.getProductCode(), 1));
                detail.setProduct(product);

                detail.setQuantityOrdered(detailDTO.getQuantityOrdered());
                detail.setPriceEach(BigDecimal.valueOf(detailDTO.getPriceEach()));
                detail.setOrderLineNumber(detailDTO.getOrderLineNumber());

                orderDetails.add(detail);
            }
            entity.setOrderDetails(orderDetails);
        }

        return entity;
    }
}
