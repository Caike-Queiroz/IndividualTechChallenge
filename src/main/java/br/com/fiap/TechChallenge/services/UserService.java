package br.com.fiap.TechChallenge.services;

import br.com.fiap.TechChallenge.dtos.ChangePasswordRequestDTO;
import br.com.fiap.TechChallenge.dtos.UserPostRequestDTO;
import br.com.fiap.TechChallenge.dtos.UserPatchRequestDTO;
import br.com.fiap.TechChallenge.entities.Address;
import br.com.fiap.TechChallenge.entities.User;
import br.com.fiap.TechChallenge.exceptions.BadRequestException;
import br.com.fiap.TechChallenge.exceptions.DuplicatedDataException;
import br.com.fiap.TechChallenge.exceptions.ResourceNotFoundException;
import br.com.fiap.TechChallenge.exceptions.WrongPasswordException;
import br.com.fiap.TechChallenge.repositories.AddressRepository;
import br.com.fiap.TechChallenge.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public Optional<User> findById(Long id) {

        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado")));
    }

    public List<User> findAllUsers(int page, int size) {

        if (page < 1 || size < 1) {
            throw new BadRequestException("Page e Size devem ser maiores que 0");
        }

        int offset = (page - 1) * size;
        return this.userRepository.findAll(size, offset);
    }

    public void saveUser(UserPostRequestDTO userDTO) {

        if (this.userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new DuplicatedDataException("Email já cadastrado");
        }

        if (this.userRepository.findByLogin(userDTO.login()).isPresent()) {
            throw new DuplicatedDataException("Login já cadastrado");
        }

        boolean isAddressPresent = this.addressRepository.findByStreetAndNum(
                userDTO.address().street(),
                userDTO.address().num()
        ).isPresent();

        if (isAddressPresent) {
            throw new DuplicatedDataException("Endereço já cadastrado");
        }

        var saveAddress = this.addressRepository.save(new Address(userDTO.address()));
        Assert.state(saveAddress == 1, "Erro ao salvar endereço");

        Optional<Address> address = this.addressRepository.findByStreetAndNum(userDTO.address().street(), userDTO.address().num());
        if (address.isEmpty()) {
            throw new ResourceNotFoundException("Endereço não encontrado");
        }

        var saveUser = this.userRepository.save(new User(userDTO, address.get()));
        Assert.state(saveUser == 1, "Erro ao salvar usuário");
    }

    public void updateUser(UserPatchRequestDTO userDTO, Long id) {

        if (this.userRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        if (
            this.userRepository.findById(id).get().getEmail().equals(userDTO.email()) &&
            this.userRepository.findById(id).get().getLogin().equals(userDTO.login())
        )
        {
            throw new DuplicatedDataException("O novo valor de login e email estão iguais aos valores atuais");
        }

        if (
            this.userRepository.findByEmail(userDTO.email()).isPresent() &&
            this.userRepository.findByEmail(userDTO.email()).get().getId() != id
        )
        {
            throw new DuplicatedDataException("Email já cadastrado");
        }

        if (
            this.userRepository.findByLogin(userDTO.login()).isPresent() &&
            this.userRepository.findByLogin(userDTO.login()).get().getId() != id
        )
        {
            throw new DuplicatedDataException("Login já cadastrado");
        }

        User user = new User(userDTO.name(), userDTO.email(), userDTO.login());

        var update = this.userRepository.update(user, id);
        if (update == 0) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
    }

    public void changePassword(ChangePasswordRequestDTO passwordRequestDTO, Long id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        if (passwordRequestDTO.currentPassword().equals(passwordRequestDTO.newPassword())) {
            throw new WrongPasswordException("Nova senha deve ser diferente da atual");
        }

        if (!user.get().getPassword().equals(passwordRequestDTO.currentPassword())) {
            throw new WrongPasswordException("Senha atual incorreta");
        }

        var update = this.userRepository.changePassword(passwordRequestDTO.newPassword(), id);
        Assert.state(update == 1, "Erro ao atualizar senha");
    }

    public void deleteUser(Long id) {

        var delete = this.userRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
    }
}