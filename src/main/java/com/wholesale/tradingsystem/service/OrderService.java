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

    public OrderDTO getOrderById(String id) {
        return orderRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EmptyResultDataAccessException("Order not found with id: " + id, 1));
    }

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    @Transactional
    public OrderDTO updateOrder(String id, OrderDTO orderDTO) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }

        Order order = convertToEntity(orderDTO);
        order.setId(id); // ensure we update the existing entity
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }

    @Transactional
    public void deleteOrder(String id) {
        if (!orderRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Order not found with id: " + id, 1);
        }
        orderRepository.deleteById(id);
    }

    public List<OrderDTO> getOrdersByCustomer(String customerId) {
        return orderRepository.findByCustomer_Id(customerId)
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
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setRequiredDate(order.getRequiredDate());
        dto.setShippedDate(order.getShippedDate());
        dto.setStatus(order.getStatus());
        dto.setComments(order.getComments());
        dto.setCustomerId(order.getCustomer().getId());

        // Convert order details
        List<OrderDTO.OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        if (order.getOrderDetails() != null) {
            orderDetailDTOs = order.getOrderDetails().stream()
                    .map(detail -> {
                        OrderDTO.OrderDetailDTO detailDTO = new OrderDTO.OrderDetailDTO();
                        detailDTO.setId(detail.getId());
                        detailDTO.setProductId(detail.getProduct().getId());
                        detailDTO.setQuantityOrdered(detail.getQuantityOrdered());
                        detailDTO.setPriceEach(detail.getPriceEach().doubleValue());
                        return detailDTO;
                    })
                    .collect(Collectors.toList());
        }
        dto.setOrderDetails(orderDetailDTOs);

        return dto;
    }

    private Order convertToEntity(OrderDTO dto) {
        Order entity = new Order();
        entity.setOrderDate(dto.getOrderDate());
        entity.setRequiredDate(dto.getRequiredDate());
        entity.setShippedDate(dto.getShippedDate());
        entity.setStatus(dto.getStatus());
        entity.setComments(dto.getComments());

        // Set customer by ID
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new EmptyResultDataAccessException("Customer not found with id: " + dto.getCustomerId(), 1));
        entity.setCustomer(customer);

        // Set order details if present
        if (dto.getOrderDetails() != null) {
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (OrderDTO.OrderDetailDTO detailDTO : dto.getOrderDetails()) {
                OrderDetail detail = new OrderDetail();
                detail.setOrder(entity);

                // Set product by ID
                Product product = productRepository.findById(detailDTO.getProductId())
                        .orElseThrow(() -> new EmptyResultDataAccessException("Product not found with id: " + detailDTO.getProductId(), 1));
                detail.setProduct(product);

                detail.setQuantityOrdered(detailDTO.getQuantityOrdered());
                detail.setPriceEach(BigDecimal.valueOf(detailDTO.getPriceEach()));

                orderDetails.add(detail);
            }
            entity.setOrderDetails(orderDetails);
        }

        return entity;
    }
}