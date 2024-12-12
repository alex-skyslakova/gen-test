import unittest
from unittest.mock import Mock, patch
import pygame
from brownian_tree import Particle, freeParticles, tree, WINDOWSIZE, SIZE, COLOR

class TestParticle(unittest.TestCase):

    def setUp(self):
        # Initialize Pygame and create a mock surface
        pygame.init()
        self.surface = pygame.Surface((WINDOWSIZE, WINDOWSIZE))
        self.vector = (5, 5)
        self.location = (50, 50)
        self.particle = Particle(self.vector, self.location, self.surface)

    def test_particle_initialization(self):
        self.assertEqual(self.particle.vector, self.vector)
        self.assertEqual(self.particle.rect.topleft, self.location)
        self.assertIn(self.particle, freeParticles)

    def test_particle_on_edge(self):
        # Test when particle hits the left edge
        self.particle.rect.left = 0
        self.particle.onEdge()
        self.assertEqual(self.particle.vector[0], abs(self.vector[0]))

        # Test when particle hits the top edge
        self.particle.rect.top = 0
        self.particle.onEdge()
        self.assertEqual(self.particle.vector[1], abs(self.vector[1]))

        # Test when particle hits the right edge
        self.particle.rect.right = WINDOWSIZE
        self.particle.onEdge()
        self.assertEqual(self.particle.vector[0], -abs(self.vector[0]))

        # Test when particle hits the bottom edge
        self.particle.rect.bottom = WINDOWSIZE
        self.particle.onEdge()
        self.assertEqual(self.particle.vector[1], -abs(self.vector[1]))

    @patch('brownian_tree.pygame.sprite.spritecollideany')
    def test_particle_update_collision_with_tree(self, mock_spritecollideany):
        # Simulate collision with tree
        mock_spritecollideany.side_effect = lambda sprite, group: group == tree
        self.particle.update()
        self.assertEqual(self.particle.vector, (0, 0))
        self.assertIn(self.particle, tree)
        self.assertNotIn(self.particle, freeParticles)

    @patch('brownian_tree.pygame.sprite.spritecollideany')
    def test_particle_update_no_collision(self, mock_spritecollideany):
        # Simulate no collision
        mock_spritecollideany.return_value = None
        self.particle.update()
        self.assertIn(self.particle, freeParticles)

    def test_particle_stop(self):
        self.particle.stop()
        self.assertEqual(self.particle.vector, (0, 0))
        self.assertIn(self.particle, tree)
        self.assertNotIn(self.particle, freeParticles)

    def test_particle_accelerate(self):
        new_vector = (10, -10)
        self.particle.accelerate(new_vector)
        self.assertEqual(self.particle.vector, new_vector)

if __name__ == '__main__':
    unittest.main()
